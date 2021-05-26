do_image_uuu[depends] += " \
	zip-native:do_populate_sysroot \
	${IMAGE_BASENAME}:do_image_wic \
"

do_image_uuu[cleandirs] += "${WORKDIR}/build-uuu"
do_image_uuu[subimages] = "zip"

IMAGE_TYPEDEP_uuu = "wic"
IMAGE_FSTYPES += "${IMAGE_TYPEDEP_uuu}"

IMAGE_CMD_uuu () {
	mkdir -p ${WORKDIR}/build-uuu

	cat << EOF > ${WORKDIR}/build-uuu/uuu.auto
uuu_version 1.2.39

SDP: boot -f u-boot-${MACHINE}.imx
FB: flash -raw2sparse mmc0 ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.wic
FB: flash mmc0boot0 u-boot-${MACHINE}.mmc
FB: flash mmc0boot1 u-boot-${MACHINE}.mmc
FB: ucmd mmc partconf \${mmcdev} 1 2 0
FB: acmd reset
FB: done
EOF

	zip --filesync --junk-paths --must-match ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.zip \
		${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.wic \
		${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.mmc \
		${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.imx \
		${WORKDIR}/build-uuu/uuu.auto
}
