require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += " \
	bc-native \
	dtc-native \
"
RDEPENDS_${PN} += "mmc-utils"

SRC_URI = " \
	git://source.denx.de/u-boot/u-boot.git;protocol=https \
	file://0005-Workaround-for-bug-when-fec-mxc-using-shared-mii.patch \
	file://fastboot_uuu.cfg \
"
SRCREV = "v${PV}"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

UBOOT_SUFFIX = "imx"
UBOOT_BINARY = "u-boot-dtb.${UBOOT_SUFFIX}"

UBOOT_SUFFIX_MMC = "mmc"
UBOOT_BINARY_MMC = "u-boot-dtb.${UBOOT_SUFFIX_MMC}"

UBOOT_IMAGE_MMC ?= "u-boot-${MACHINE}-${PV}-${PR}.${UBOOT_SUFFIX_MMC}"
UBOOT_SYMLINK_MMC ?= "u-boot-${MACHINE}.${UBOOT_SUFFIX_MMC}"

do_compile_append () {
	dd if=${B}/${UBOOT_BINARY} of=${B}/${UBOOT_BINARY_MMC} bs=1024 seek=1 status=none
}

do_deploy_append () {
	install -D -m 644 ${B}/${UBOOT_BINARY_MMC} ${DEPLOYDIR}/${UBOOT_IMAGE_MMC}

	cd ${DEPLOYDIR}
	rm -f ${UBOOT_BINARY_MMC} ${UBOOT_SYMLINK_MMC}
	ln -sf ${UBOOT_IMAGE_MMC} ${UBOOT_SYMLINK_MMC}
	ln -sf ${UBOOT_IMAGE_MMC} ${UBOOT_BINARY_MMC}
}

pkg_postinst_ontarget_${PN} () {
	BOOT_PART=$(mmc extcsd read /dev/mmcblk0 | egrep -o 'Boot Partition ([12]) enabled' | tr -dc '1-2')
	if (($BOOT_PART == 1)); then
		BOOT_PART=2
		BOOT_DEV=1
	else
		BOOT_PART=1
		BOOT_DEV=0
	fi

	echo 0 > /sys/block/mmcblk0boot${BOOT_DEV}/force_ro
	dd if=$D/boot/${UBOOT_BINARY} of=/dev/mmcblk0boot${BOOT_DEV} bs=1024 seek=1 status=none
	echo 1 > /sys/block/mmcblk0boot${BOOT_DEV}/force_ro

	mmc bootpart enable ${BOOT_PART} 1 /dev/mmcblk0
}
