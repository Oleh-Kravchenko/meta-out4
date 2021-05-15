SUMMARY = "Kernel source tree for Out4 compatible boards."
SECTION = "kernel"
HOMEPAGE = "https://www.kernel.org"
BUGTRACKER = "https://bugzilla.kernel.org"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRCREV = "v${PV}"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;protocol=https;branch=linux-5.10.y"

COMPATIBLE_MACHINE = "ev-imx280-nano-x-mb|o4-imx6ull-nano"
KBUILD_DEFCONFIG ?= "imx_v6_v7_defconfig"
KCONFIG_MODE = "--alldefconfig"

require recipes-kernel/linux/linux-yocto.inc
