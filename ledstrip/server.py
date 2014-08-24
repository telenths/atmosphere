#!/usr/bin/env python

import socket
import sys

from raspledstrip.ledstrip import *
from time import sleep

led = LEDStrip(18 * 2 + 32)
led.setChannelOrder(ChannelOrder.BRG)
#led.setMasterBrightness(0.9)

for i in range (0, 255):
  led.fillHue(i)
  led.update()
  sleep(1)

#led.fillRGB(255, 0, 0)
#led.update()
#led.update()
sleep(1)
#led.fillRGB(0, 255, 0)
#led.update()
#sleep(1)
#led.fillRGB(0, 0, 255)
#led.update()
#sleep(1)
led.all_off()

HOST = ''       # Symbolic name, meaning all available interfaces
PORT = 8888     # Arbitrary non-privileged port
BUFFER = 2048

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print 'Socket created'

#Bind socket to local host and port
try:
    s.bind((HOST, PORT))
except socket.error as msg:
    print 'Bind failed. Error Code : ' + str(msg[0]) + ' Message ' + msg[1]
    sys.exit()

print 'Socket bind complete'

#Start listening on socket
s.listen(1)
print 'Socket now listening'

while 1:
    conn, addr = s.accept()
#    print 'Connected with ' + addr[0] + ':' + str(addr[1])
    while 1:
        try:
           recv = conn.recv(BUFFER)
#           print('Client Send: %s' % recv )
           if not recv:
               conn.close()
               break
           if recv.startswith('LED '):
               strLedColors = recv.strip().lstrip('LED ').rstrip(',')
    #           print strLedColors
               ledColors = strLedColors.split(',')
               for strPixcel in ledColors:
                    str = strPixcel.strip().split()
    #                print str
                    r = int(str[0])
                    g = int(str[1])
                    b = int(str[2])
                    i = int(str[3])
                    led.fillRGB(r,g,b,i,i)
               led.update()
           if recv.startswith('LEDOFF'):
               led.all_off()
        except:
            break

s.close()
