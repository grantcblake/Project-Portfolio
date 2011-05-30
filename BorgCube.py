##A program modified from an earlier version of the moon orbiting the earth
##Deals with a "Borg cube" with a smaller mass and thrusters

from visual import *
from time import *

scene=display(height=800,width=800,x=200,y=0,center=(0,0,0),
              range=vector(5e8,5e8,1e6))

def scinot (num):
    exp = 0
    while num >= 10:
        num=num/10.
        exp += 1
    scinot.exp=exp
    scinot.num=num
    return ("%1.3f*10^%1.0f" %(num, exp))

G=6.67*10**-11 #m^3/kg*s^2
M=5.97*10**24  #kg
m=1000         #kg
r=3.85*10**8   #m
v=1022         #m/s
t=0            #s
dt=10          #s

Eradius = 6.3*10**6   #m
Bradius = 4*1.7*10**6 #m

FG = G*m*M/r**2 #Gravity
FT = vector(0,0,0) #thrusters
Fnet = vector()

Earth = sphere(pos=(0,0,0),radius=Eradius*5,material=materials.earth)

#Borg cube with thrusters: w,a,s,d control movement
Borg = frame(pos=(r,0,0)) 
Main = box(frame=Borg,pos=(0,0,0),size=(Bradius*2,Bradius*2,Bradius*2))
T1 = arrow(frame=Borg,pos=(0,-Bradius,0),length=Bradius,axis=(0,-1,0))
T2 = arrow(frame=Borg,pos=(-Bradius,0,0),length=Bradius,axis=(-1,0,0))
T3 = arrow(frame=Borg,pos=(0,Bradius,0),length=Bradius,axis=(0,1,0))
T4 = arrow(frame=Borg,pos=(Bradius,0,0),length=Bradius,axis=(1,0,0))

#Second Borg cube with no thrusters to track original path
Borgtracker = box(pos=(r,0,0),color=color.green,
                  size=(Bradius*2,Bradius*2,Bradius*2))

Aborg = Fnet/m
Aearth = norm(Earth.pos)*FG/M
Vborg = vector(0,v,0)
Vearth = vector(0,0,0)
Sborg = Borg.pos
Searth = Earth.pos

Ab2 = Fnet/m
Vb2 = vector(0,v,0)
Sb2 = Borgtracker.pos

V = (Vborg.x**2+Vborg.y**2+Vborg.z**2)**(1./2.)
kE = (1/2)*m*V**2
gE = -G*m*M/r

kLabel = label(pos=Earth.pos, yoffset=50)
gLabel = label(pos=Earth.pos, yoffset=-50)
tLabel = label(pos=Earth.pos)
InstructionLabel = label(pos=(-1.3e8,4.45e8,0),
                         text='The white cube has thrusters. w for up, s for down, a for left, d for right.')

while true:

    if scene.kb.keys:
        k = scene.kb.getkey()
        if len(k) == 1:
            if k == 's':
                FT = .1*vector(0,-1,0)
                #print 's'
            if k == 'a':
                FT = .1*vector(-1,0,0)
                #print 'a'
            if k == 'w':
                FT = .1*vector(0,1,0)
                #print 'w'
            if k == 'd':
                FT = .1*vector(1,0,0)
                #print 'd'
    
    FGrav = -norm(Borg.pos)*FG #update movement of borg cube
    Fnet = FGrav + FT
    Aborg = Fnet/m
    Vborg += Aborg*dt
    Sborg += Vborg*dt

    FG2 = -norm(Borgtracker.pos)*FG #update movement of borg cube tracker
    Ab2 = FG2/m
    Vb2 += Ab2*dt
    Sb2 += Vb2*dt
    Borgtracker.pos = Sb2

    #keep track of energy values
    Borg.pos=Sborg
    r = ((Earth.pos.x-Borg.pos.x)**2+(Earth.pos.y-Borg.pos.y)**2+(Earth.pos.z-Borg.pos.z)**2)**(1./2.)
    V = (Vborg.x**2+Vborg.y**2+Vborg.z**2)**(1./2.)
    kE = (1./2.)*m*V**2
    gE = -G*m*M/r
    E = kE+gE

    kLabel.text = 'KE = %1.2e Joules' %kE
    gLabel.text = 'GE = %1.2e Joules' %gE
    tLabel.text = 'E = %1.2e Joules' %E
