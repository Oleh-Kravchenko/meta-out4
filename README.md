# meta-out4

Yocto BSP layer for the [Out4](https://www.out4.ru/) boards.

## O4-iMX-NANO

`MACHINE = "o4-imx6ull-nano"`

- CPU reset button
- TTL 3.3V UART Debug port
- MicroSD card reader
- two USB power controlled ports
- two Ethernet 10/100 ports
- two RGB LEDs
- CAN port
- RS485 Port
- 6-15V power supply
- GPIO headers

![1]

## EV-iMX280-NANO-X-MB

`MACHINE = "ev-imx280-nano-x-mb"`

- CPU reset button
- DB9 UART Debug port
- MicroSD card reader
- two USB ports
- Ethernet 10/100 port
- 5V power supply
- GPIO headers

![2]

# Quick start

1. initialize environment by `source poky/oe-init-build-env build`
2. add this layer to `bblayers.conf`
3. set MACHINE in local.conf to one of the supported boards
4. set or add 'uuu' to `IMAGE_FSTYPES`
5. build image (e.g. `bitbake core-image-base`)
6. use [Universal Update Utility][5] to flash `core-image-base-<MACHINE>.zip` file
7. boot your board

# Maintainers

- Oleh Kravchenko `<oleg at kaa.org.ua>`

[1]: img/O4-iMX-NANO.png
[2]: img/EV-iMX280-NANO-X-MB.png
[3]: http://evodbg.net/products/mx28-eval-kits/14-ev-imx280-nano-x-mb.html
[4]: https://out4.ru/products/board/18-o4-imx-nano.html
[5]: https://github.com/NXPmicro/mfgtools "uuu (Universal Update Utility)"
[6]: doc/ev-imx280-nano-x-mb/uuu.auto
[7]: doc/o4-imx6ull-nano/uuu.auto
