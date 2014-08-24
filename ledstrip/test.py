import socket
import sys

from raspledstrip.ledstrip import *
from time import sleep

led = LEDStrip(18 * 2 + 32)
led.setChannelOrder(ChannelOrder.BRG)

led.setMasterBrightness(0.0)

for i in range (0,7):
  led.fillRGB(255,0,0)
  led.setMasterBrightness(i / 10.0)
  led.update()
  sleep(0.1)

for i in range (0, 200):
  hue = 360 / 200.0 * i
  led.fillHue( hue )
  led.setMasterBrightness(0.7)
  led.update()
  sleep(0.1)





led.all_off()

