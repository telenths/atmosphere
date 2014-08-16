#!/usr/bin/env python

import socket
import sys

from raspledstrip.ledstrip import *
from time import sleep

led = LEDStrip(18 * 2 + 32)
led.fillRGB(128, 128, 128)
led.update()
led.update()
sleep(1)
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
s.listen(10)
print 'Socket now listening'

while 1:
    conn, addr = s.accept()
    print 'Connected with ' + addr[0] + ':' + str(addr[1])
    while 1:
       recv = conn.recv(BUFFER)
       print('Client Send: %s' % recv )
       if not recv:
           conn.close()
           break
       if recv.startswith('LED '):
           str = recv.split()
           r = int(str[1])
           g = int(str[2])
           b = int(str[3])
           i = int(str[4])
           led.fillRGB(r,g,b,i,i)
           led.update()
       if recv.startswith('LEDOFF'):
           led.all_off()


s.close()
