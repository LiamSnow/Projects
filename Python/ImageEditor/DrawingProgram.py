import math

import pygame
from pygame.locals import*

from tkinter import Tk
from tkinter.filedialog import askopenfilename

import PIL
from PIL import Image

import random
from random import *

import numpy as np

ImageWidth  = 0
ImageHeight = 0
pixels = []

def DefaultImage(width, height):
    global ImageWidth
    global ImageHeight
    global pixels
    ImageWidth  = width
    ImageHeight = height
    pixels = []
    for i in range(ImageWidth*ImageHeight):
        pixels.append((randint(0,255),randint(0,255),randint(0,255),100))

OpenFile = False

if OpenFile == True:
    root = Tk()
    ftypes = [('PNG',"*.png")]
    ttl  = "Title"
    dir1 = 'C:\\'
    root.fileName = askopenfilename(filetypes = ftypes, initialdir = dir1, title = ttl)
    print (root.fileName)

    if root.fileName != '':
        im = Image.open(root.fileName)
        ImageWidth  = im.width
        ImageHeight = im.height
        pixels = list(im.getdata())
    else:
        DefaultImage(100,100)
else:
    DefaultImage(100,100)

pygame.init()
window = (2560,1440)
#window = (int(window[0]/4), int(window[1]/4))
screen = pygame.display.set_mode(window,pygame.FULLSCREEN)
background = pygame.Surface(window)
pygame.display.set_caption('Test')
clock = pygame.time.Clock()
crashed = False;

width  = pygame.display.get_surface().get_size()[0]
height = pygame.display.get_surface().get_size()[1]
centerX = width/2
centerY = height/2
white = (255,255,255)
mouseX = pygame.mouse.get_pos()[0]
mouseY = pygame.mouse.get_pos()[1]
PmouseX = mouseX
PmouseY = mouseY
mouseDown = False
pmouseDown= False
scale = 100
adjX = 0
adjY = 0
menuColor = (20,20,20)
cTool = -1
uTool = False
eTool = False
drawColor = (0,0,0,255)
pdrawColor = (0,0,0,255)

background.fill(white)

DEFAULT_CURSOR = pygame.mouse.get_cursor()
_HAND_CURSOR = (
"     XX         ",
"    X..X        ",
"    X..X        ",
"    X..X        ",
"    X..XXXXX    ",
"    X..X..X.XX  ",
" XX X..X..X.X.X ",
"X..XX.........X ",
"X...X.........X ",
" X.....X.X.X..X ",
"  X....X.X.X..X ",
"  X....X.X.X.X  ",
"   X...X.X.X.X  ",
"    X.......X   ",
"     X....X.X   ",
"     XXXXX XX   ")
_HCURS, _HMASK = pygame.cursors.compile(_HAND_CURSOR, ".", "X")
HAND_CURSOR = ((16, 16), (5, 1), _HCURS, _HMASK)

_RECT_CURSOR = (
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",
"xxxxxxxxxxxxxxxx",)
_RCURS, _RMASK = pygame.cursors.compile(_RECT_CURSOR, ".", "X")
RECT_CURSOR = ((16, 16), (5, 1), _RCURS, _RMASK)

## Extra Functions ##########################################################################################################

    #Clamping float32 between two float32s
def clamp(num, min, max):
    if num > max:
        return max
    elif num < min:
        return min
    else:
        return num

cCh = 0
cChD = True
def CheckerBox(x1,y1,xs,ys):
    tiles = 10
    rTi = (10,10)
    global background
    global cCh
    global cChD
    cC = False
    if cChD == True:
        cCh += 10
    else:
        cCh -= 10
    if cCh > 1000 or cCh < -1000:
        cChD = not cChD
    for h in range(y1,y1+ys,rTi[1]):
        cC = not cC
        for w in range(x1,x1+xs,rTi[0]):
            cC = not cC
            if cC == True:
                rC = (clamp(0+cCh,0,255),clamp(0+cCh,0,255),clamp(0+cCh,0,255))
            else:
                rC = (clamp(255-cCh,0,255),clamp(255-cCh,0,255),clamp(255-cCh,0,255))
            pygame.draw.rect(background, rC, (w,h,rTi[0],rTi[1]), 0)

def getMP():
    return pygame.mouse.get_pos()
def getMD():
    pygame.mouse.get_pressed()[0]

def toolPos(Ty):
    return [int(wcX/1.1)-25, (int(height/3.5)-25)+(Ty*70), 50, 50]

def toolDraw(Ty):
    global background
    global menuColor
    global pmouseDown
    global mouseDown
    global cTool
    pygame.draw.rect(background, (0,0,0), toolPos(Ty), 2)
    if toolClicked(Ty):
        if cTool == Ty:
            pygame.draw.rect(background, (clamp(menuColor[0]-50,0,255),clamp(menuColor[1]+100,0,255),clamp(menuColor[2]-100,0,255)), [toolPos(Ty)[0]+2,toolPos(Ty)[1]+2,toolPos(Ty)[2]-3,toolPos(Ty)[3]-3], 0)
        else:
            pygame.draw.rect(background, (clamp(menuColor[0]+30,0,255),clamp(menuColor[1]+30,0,255),clamp(menuColor[2]+50,0,255)), [toolPos(Ty)[0]+2,toolPos(Ty)[1]+2,toolPos(Ty)[2]-3,toolPos(Ty)[3]-3], 0)
    elif cTool == Ty:
        pygame.draw.rect(background, (clamp(menuColor[0]-100,0,255),clamp(menuColor[1]+100,0,255),clamp(menuColor[2]-100,0,255)), [toolPos(Ty)[0]+2,toolPos(Ty)[1]+2,toolPos(Ty)[2]-3,toolPos(Ty)[3]-3], 0)
    if mouseDown == True and pmouseDown == False:
        if toolClicked(Ty):
            if Ty == cTool:
                cTool = -1
            else:
                cTool = Ty

def toolClicked(Ty):
    global mouseX
    global mouseY
    if mouseX >= int(wcX/1.1)-25 and mouseX <= (int(wcX/1.1)-25)+50 and mouseY >= (int(height/3.5)-25)+(Ty*60) and mouseY <= ((int(height/3.5)-25)+(Ty*60))+50:
        return True
    else:
        return False

def inRect(x1,y1,xs,ys):
    global mouseX
    global mouseY
    if mouseX >= x1 and mouseX <= x1+xs and mouseY >= y1 and mouseY <= y1+ys:
        return True
    else:
        return False

def roundTo(num, to):
    return (round(num/to))*to

def setDrawColor(r,g,b):
    global pdrawColor
    global drawColor
    pdrawColor = drawColor
    drawColor = (r,g,b,255)

def plot(x,y):
    global drawColor
    if math.floor((y*(ImageWidth))+x) < len(pixels):
        pixels[math.floor((y*(ImageWidth))+x)] = drawColor

def screenToPixel(x,y):
    global tcX, tcY, ImageWidth, ImageHeight, scale, dW
    xc = screenToCanvasX(x)
    yc = screenToCanvasY(y)
    return math.floor((yc*(ImageWidth))+xc)
def screenToCanvasX(x):
    return math.floor(((x-int(tcX))/int((scale/dW)*2))*ImageWidth)
def screenToCanvasY(y):
    return math.floor(((y-int(tcY))/int(scale*2))*ImageHeight)

def rectCursor(size):
    global _RCURS, _RMASK, RECT_CURSOR
    RECT_CURSOR = (size, (5, 1), _RCURS, _RMASK)

## Main Script ##############################################################################################################

while not crashed:
    #Set Common Variables
    mouseX = pygame.mouse.get_pos()[0]
    mouseY = pygame.mouse.get_pos()[1]
    mouseDown = pygame.mouse.get_pressed()[0]

    #Refresh Screen
    background.fill((150,150,150))

    #Keyboard Input
    #   Zoom In/Out
    if (pygame.key.get_pressed()[pygame.K_UP] != 0):
        scale = clamp(scale + 10, 10,1000)
    if (pygame.key.get_pressed()[pygame.K_DOWN] != 0):
        scale = clamp(scale - 10, 10,1000)
    #   Adjust Image - Left,Right,Up and Down
    if (pygame.key.get_pressed()[pygame.K_d] != 0):
        adjX -= scale/50
    if (pygame.key.get_pressed()[pygame.K_a] != 0):
        adjX += scale/50
    if (pygame.key.get_pressed()[pygame.K_s] != 0):
        adjY -= scale/50
    if (pygame.key.get_pressed()[pygame.K_w] != 0):
        adjY += scale/50
    #   Blurring
    if (pygame.key.get_pressed()[pygame.K_b] != 0):
        if not hpB:
            hpB = True
            BlrA = 10
            baL = (int(ImageWidth*(BlrA/100)),int(ImageHeight*(BlrA/100)))
            for h in range(0,ImageHeight,int(baL[1]/5)):
                for w in range(baL[0]-1,ImageWidth,int(baL[0]/5)):
                    ref = (ImageWidth*h)+w
                    bT = []
                    for gt in range(0,(baL[1])):
                        for gb in range(0,(baL[0])):
                            if (((ref + (ImageWidth*gt)) - gb)) < len(pixels):
                                bT.append(((ref + (ImageWidth*gt)) - gb))
                    rR = 0
                    rG = 0
                    rB = 0
                    for c in range(len(bT)):
                        rR += pixels[bT[c]][0]
                        rG += pixels[bT[c]][1]
                        rB += pixels[bT[c]][2]
                    rR /= len(bT)
                    rG /= len(bT)
                    rB /= len(bT)
                    for e in range(len(bT)):
                        pixels[bT[e]] = (rR,rG,rB,255)
    else:
        hpB = False

    #Mouse Input
    #if mouseDown:

    #Main Drawing Square
    dW = ImageHeight/ImageWidth
    dscX = centerX + adjX
    dscY = centerY + adjY
    tcX = (dscX - (scale/dW))
    tcY = dscY - scale
    leX = ((scale/dW)*2)
    leY = ((scale)*2)
    lcX = tcX + leX
    lcY = tcY + leY
    CheckerBox(int(tcX), int(tcY), int((scale/dW)*2), int(scale*2))

    #Draw In pixels
    for h in range(ImageHeight):
        for w in range(ImageWidth):
            ap = (ImageHeight*(h-ImageWidth))+w
            pygame.draw.rect(background, (pixels[ap][0],pixels[ap][1],pixels[ap][2]), (tcX+((w/ImageWidth)*leX),tcY+((h/ImageHeight)*leY),leX/ImageWidth+1,leY/ImageHeight+1), 0)

    #Draw Outer Line of "Main Drawing Square"
    OutlineColor = (100,100,0)
    pygame.draw.line(background, OutlineColor, (tcX,tcY), (tcX,lcY), 2)
    pygame.draw.line(background, OutlineColor, (lcX,tcY), (lcX,lcY), 2)
    pygame.draw.line(background, OutlineColor, (tcX,tcY), (lcX,tcY), 2)
    pygame.draw.line(background, OutlineColor, (tcX,lcY), (lcX,lcY), 2)

    #Draw Grid Lines
    if (leX/ImageWidth) > 5:
        for i in range(1,ImageWidth):
            pygame.draw.line(background, (230,230,200), (tcX + ((i/ImageWidth)*leX),tcY), (tcX + ((i/ImageWidth)*leX),lcY), 1)
        for i in range(1,ImageHeight):
            pygame.draw.line(background, (230,230,200), (tcX, tcY + ((i/ImageHeight)*leY)), (lcX, tcY + ((i/ImageHeight)*leY)), 1)

    #Menu
    pygame.draw.rect(background, menuColor, (0, 0, int(width/3.3), height), 0)
    pygame.draw.rect(background, menuColor, (int(width/3.3), int(height-(height/3.65)), width-int(width/3.3), int(height/3.5)), 0)
    pygame.draw.line(background, (20,20,100), (int(width/3.3),0), (int(width/3.3),int(height-(height/3.65))), 3)
    pygame.draw.line(background, (20,20,100), (int(width/3.3),int(height-(height/3.65))), (width,int(height-(height/3.65))), 3)

    #   Mouse Lines
    mlSize = (height/3.65)/25
    mlSize = int(mlSize)
    pygame.draw.line(background, (20,20,100), ((clamp(mouseX,int(width/3.3),width)),int(height-(height/3.65))), ((clamp(mouseX,int(width/3.3),width)),int(height-(height/3.65))+mlSize), 2)
    pygame.draw.line(background, (20,20,100), (int(width/3.3), clamp(mouseY,0,int(height-(height/3.65)))), ((int(width/3.3)-mlSize, clamp(mouseY,0,int(height-(height/3.65))))), 2)

    #   Scrolling
    wcX = int(width/3.3)
    wcY = int(height-(height/3.65))
    wsX = width - wcX
    wsY = height-(height-wcY)
    pygame.draw.rect(background, (0,0,0), (clamp(tcX,wcX,width), wcY, wsX/10, -10), 0)
    pygame.draw.rect(background, (0,0,0), (wcX, clamp(tcY, 0, ((wcY-(wsX/10))+1)), 10, wsX/10), 0)
    if mouseDown == True:
        if pmouseDown == False:
            if mouseX >= (clamp(tcX,wcX,width)) and mouseX <= ((clamp(tcX,wcX,width)+(wsX/10))) and mouseY <= wcY and mouseY >= (wcY-10):
                ssX = True
                pygame.mouse.set_cursor(*HAND_CURSOR)
            if mouseX >= wcX and mouseX <= (wcX+10) and mouseY >= clamp(tcY, 0, ((wcY-(wsX/10))+1)) and mouseY <= clamp(tcY, 0, ((wcY-(wsX/10))+1))+(wsX/10):
                ssY = True
                pygame.mouse.set_cursor(*HAND_CURSOR)
    else:
        ssX = False
        ssY = False
        pygame.mouse.set_cursor(*DEFAULT_CURSOR)
    if ssX == True:
        adjX = (mouseX-centerX)+(scale/dW)-(wsX/20)
    if ssY == True:
        adjY = ((mouseY-centerY)+scale)-(wsX/20)

    #   Tool Images and Buttons
    toolDraw(0)
    toolDraw(1)
    toolDraw(2)

    #Color Signals
    pygame.draw.rect(background, (0,0,0), [int(wcX/1.05)-25, wcY-50, 50, 50], 2)
    pygame.draw.rect(background, drawColor, [int(wcX/1.05)-25+2, wcY-50+2, 50-3, 50-3], 0)

    pygame.draw.rect(background, (0,0,0), [int(wcX/1.15)-10, wcY-40, 40, 40], 2)
    pygame.draw.rect(background, pdrawColor, [int(wcX/1.15)-10+2, wcY-40+2, 40-3, 40-3], 0)
    if inRect(int(wcX/1.15)-10, wcY-40, 40, 40) and pmouseDown == False and mouseDown == True:
        refC = [drawColor, pdrawColor]
        drawColor = refC[1]
        pdrawColor = refC[0]

    # Tool Functions
    if mouseX >= wcX and mouseY <= wcY and ssX == False and ssY == False and mouseDown == True:
        if pmouseDown == False:
            uTool  = True
            eTool  = True
    else:
        uTool = False

    if cTool == 0 and uTool == True:
        if inRect(int(tcX), int(tcY), int((scale/dW)*2), int(scale*2)):
            x1 = screenToCanvasX(mouseX)
            y1 = screenToCanvasY(mouseY)
            x0 = screenToCanvasX(PmouseX)
            y0 = screenToCanvasY(PmouseY)
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

    if cTool == 1 and eTool == True:
        if inRect(int(tcX), int(tcY), int((scale/dW)*2), int(scale*2)):
            setDrawColor(pixels[screenToPixel(mouseX,mouseY)][0],pixels[screenToPixel(mouseX,mouseY)][1],pixels[screenToPixel(mouseX,mouseY)][2])

    #Mouse Cursor
    #if mouseX >= wcX and mouseY <= wcY and ssX == False and ssY == False:
        #rectCursor([roundTo(leX/ImageWidth+1,8),roundTo(leY/ImageHeight+1,8 )])
        #pygame.mouse.set_cursor(*RECT_CURSOR)
    #else:
        #pygame.mouse.set_cursor(*DEFAULT_CURSOR)

    #Setting Common Variables
    PmouseX = mouseX
    PmouseY = mouseY
    pmouseDown = mouseDown
    eTool = False

    #Stop Program
    for event in pygame.event.get():
        if event.type == pygame.QUIT or (event.type == pygame.KEYDOWN and event.key == pygame.K_ESCAPE):
            crashed = True

    #Update Display and Tick 60 Frames
    #pa = pygame.surfarray.array2d(background)
    #pygame.display.update(pygame.surfarray.blit_array(screen, pa))
    screen.blit(background,(0,0))
    pygame.display.update()
    #clock.tick(60)

#Finalize the Quitting After Program has Been Stopped
pygame.quit()
quit()
