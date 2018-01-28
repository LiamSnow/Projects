import math
import pygame
from pygame.locals import*

import argparse
import sys
from tensorflow.examples.tutorials.mnist import input_data
import tensorflow as tf
from PIL import Image
import time

pygame.init()
screen = pygame.display.set_mode((1000,500))
pygame.display.set_caption('Test')
clock = pygame.time.Clock()
crashed = False;

mouseX = pygame.mouse.get_pos()[0]
mouseY = pygame.mouse.get_pos()[1]
PmouseX = mouseX
PmouseY = mouseY

mouseDown = False;
DebugMessage = "????"

width  = pygame.display.get_surface().get_size()[0]
height = pygame.display.get_surface().get_size()[1]
height = width

white = (255,255,255)

ImageWidth  = 28
ImageHeight = ImageWidth

pixels = []
pixelsLen = ImageWidth*ImageHeight
for p in range (0, ImageWidth*ImageHeight):
    pixels.append(0.0)

pygame.font.init()

lines = [[(1,1),(1,1)],[(1,1),(1,1)]]

###################################################################################################################################################

def plot(py, px):
    placeAt = ((math.floor(py/(height/ImageHeight)))*ImageHeight)+(math.floor(px/(width/ImageWidth)))
    if (placeAt < pixelsLen):
        pixels[placeAt] = 1.0

###################################################################################################################################################

hasRestored = False
def readNumber():
    global hasRestored
    if True:
        x = tf.placeholder(tf.float32, [None, 784])
        W = tf.Variable(tf.zeros([784, 10]))
        b = tf.Variable(tf.zeros([10]))

        def weight_variable(shape):
          initial = tf.truncated_normal(shape, stddev=0.1)
          return tf.Variable(initial)

        def bias_variable(shape):
          initial = tf.constant(0.1, shape=shape)
          return tf.Variable(initial)

        def conv2d(x, W):
          return tf.nn.conv2d(x, W, strides=[1, 1, 1, 1], padding='SAME')

        def max_pool_2x2(x):
          return tf.nn.max_pool(x, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding='SAME')

        W_conv1 = weight_variable([5, 5, 1, 32])
        b_conv1 = bias_variable([32])

        x_image = tf.reshape(x, [-1,28,28,1])
        h_conv1 = tf.nn.relu(conv2d(x_image, W_conv1) + b_conv1)
        h_pool1 = max_pool_2x2(h_conv1)

        W_conv2 = weight_variable([5, 5, 32, 64])
        b_conv2 = bias_variable([64])

        h_conv2 = tf.nn.relu(conv2d(h_pool1, W_conv2) + b_conv2)
        h_pool2 = max_pool_2x2(h_conv2)

        W_fc1 = weight_variable([7 * 7 * 64, 1024])
        b_fc1 = bias_variable([1024])

        h_pool2_flat = tf.reshape(h_pool2, [-1, 7*7*64])
        h_fc1 = tf.nn.relu(tf.matmul(h_pool2_flat, W_fc1) + b_fc1)

        keep_prob = tf.placeholder(tf.float32)
        h_fc1_drop = tf.nn.dropout(h_fc1, keep_prob)

        W_fc2 = weight_variable([1024, 10])
        b_fc2 = bias_variable([10])

        y_conv=tf.nn.softmax(tf.matmul(h_fc1_drop, W_fc2) + b_fc2)

        init_op = tf.initialize_all_variables()
        saver = tf.train.Saver()

    with tf.Session() as sess:
        sess.run(init_op)
        if not hasRestored:
            saver.restore(sess, "model2.ckpt")
            hasRestored = True
        prediction=tf.argmax(y_conv,1)
        numberPred = prediction.eval(feed_dict={x: [pixels],keep_prob: 1.0}, session=sess)
    #print(numberPred)
    global DebugMessage
    DebugMessage = str(numberPred)

############################################################################################################################################

Cwp = False
while not crashed:
    mouseX = pygame.mouse.get_pos()[0]
    mouseY = pygame.mouse.get_pos()[1]
    mouseDown = pygame.mouse.get_pressed()[0]
    pixelsLen = len(pixels)

    screen.fill(white)

    if (pygame.key.get_pressed()[pygame.K_UP]   != 0):
        width  += 10
        height += 10
    if (pygame.key.get_pressed()[pygame.K_DOWN] != 0):
        width  -= 10
        height -= 10
    if (pygame.key.get_pressed()[pygame.K_c] != 0):
        if not Cwp:
            Cwp = True
            print(pixels)
            for s in range(0,pixelsLen):
                pixels[s] = 0
    else:
        Cwp = False
    if (pygame.key.get_pressed()[pygame.K_v] != 0):
        readNumber()

    for i in range (0, ImageWidth+1):
        pygame.draw.line(screen, (200,200,200), (i*(width/ImageWidth),0), (i*(width/ImageWidth),height), 1)
    for i in range (0, ImageHeight+1):
        pygame.draw.line(screen, (200,200,200), (0,i*(height/ImageHeight)), (width,i*(height/ImageHeight)), 1)

    for i in lines:
        pygame.draw.line(screen, (0,255,0), i[0], i[1],1)

    if mouseDown:
        #lines.append([(PmouseX,PmouseY),(mouseX,mouseY)]) <-- Debug Lines of Error
        x1 = mouseY
        y1 = mouseX
        x0 = PmouseY
        y0 = PmouseX
        dx = abs(x1 - x0)
        dy = abs(y1 - y0)
        x, y = x0, y0
        sx = -1 if x0 > x1 else 1
        sy = -1 if y0 > y1 else 1
        if dx > dy:
            err = dx / 2.0
            while x != x1:
                plot(x,y)
                err -= dy
                if err < 0:
                    y += sy
                    err += dx
                x += sx
        else:
            err = dy / 2.0
            while y != y1:
                plot(x,y)
                err-=dx
                if err < 0:
                    x += sx
                    err += dy
                y += sy
        plot(x,y)

    for a in range (0, pixelsLen):
        if pixels[a] == 1:
            pos = [a%ImageHeight,math.floor(a/ImageWidth)]
            pygame.draw.rect(screen, (0,0,0), (pos[0]*(width/ImageWidth)+1,pos[1]*(height/ImageHeight)+1,width/ImageWidth-2,height/ImageHeight-2), 0)

    PmouseX = mouseX
    PmouseY = mouseY

    fontm = pygame.font.SysFont('Comic Sans MS', 35)
    TextSurf = fontm.render(DebugMessage, False, (0,0,0))
    screen.blit(TextSurf, (0,0))

    for event in pygame.event.get():
        if event.type == pygame.QUIT or (event.type == pygame.KEYDOWN and event.key == pygame.K_ESCAPE):
            crashed = True

    pygame.display.update()
    clock.tick(60)

###################################################################################################################################################

#print(pixels)
pygame.quit()
quit()
