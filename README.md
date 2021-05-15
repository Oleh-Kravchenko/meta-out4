# meta-out4

Hardware layer for Out4 boards.

## Manufacturing

[uuu (Universal Update Utility), mfgtools 3.0](https://github.com/NXPmicro/mfgtools).

uuu.auto for O4-iMX-NANO:

	uuu_version 1.2.39

	SDP: boot -f u-boot-o4-imx6ull-nano.imx
	FB: flash -raw2sparse mmc0 core-image-minimal-o4-imx6ull-nano.wic
	FB: flash mmc0boot0 u-boot-o4-imx6ull-nano.mmc
	FB: flash mmc0boot1 u-boot-o4-imx6ull-nano.mmc
	FB: ucmd mmc partconf ${mmcdev} 1 2 0
	FB: acmd reset
	FB: done

uuu.auto for EV-iMX280-NANO-X-MB:

	uuu_version 1.2.39

	SDP: boot -f u-boot-ev-imx280-nano-x-mb.imx
	FB: flash -raw2sparse mmc0 core-image-minimal-ev-imx280-nano-x-mb.wic
	FB: flash mmc0boot0 u-boot-ev-imx280-nano-x-mb.mmc
	FB: flash mmc0boot1 u-boot-ev-imx280-nano-x-mb.mmc
	FB: ucmd mmc partconf ${mmcdev} 1 2 0
	FB: acmd reset
	FB: done

## Tips & Tricks

Disable booting from eMMC:

	mc partconf 0 0 0 0
