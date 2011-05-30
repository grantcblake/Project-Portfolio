##A program modeling electrons moving through the magnetic field.
##Arrows are present in the back to represent the magnetic field -
##colors are randomly generated for each one just for fun.
##Twenty electrons start out with 20 different voltages so that the
##initial x velocity is different for each one.

from visual import *

scene=display(range=vector(.04,.04,.04),center=(0,0,0),x=200,y=0,
              height=800,width=800,title='Magnetic Madness!')

def sqrt(num):
    return (num)**(1./2.)

def dot (S1, S2):
    return S1.x*S2.x+S1.y*S2.y+S1.z*S2.z

def cross (S1, S2):
    return vector((S1.y*S2.z-S1.z*S2.y),(S1.z*S2.x-S1.x*S2.z),
                  (S1.x*S2.y-S1.y*S2.x))

N = 20 #length of array
B = vector(0,0,.004) #magnetic field
t=0 #seconds
dt=5e-13 #seconds

green=random.random()
blue=random.random()

electron=[]
for i in range(N):
    electron += [sphere(radius=5e-3,pos=(0,-.03,0),charge=-1.602*10**(-19),
                        Voltage=(300+i*20),mass=9.11*10**(-31),
                        color=((.04*i+.01),green,blue))]

for i in range(N):
    electron[i].velocity = vector(sqrt(2*abs(electron[i].charge)*electron[i].Voltage/electron[i].mass),
                                  i*4e5,3e3)
for x in range(12):
    for y in range(12):
        arrow(pos=((-.038+x*.007),(-.038+y*.007),-.008),axis=vector(0,0,.008),
              color=(random.random(),random.random(),random.random()))
        
while True:
    for i in range(N):
        electron[i].force=electron[i].charge*cross(electron[i].velocity,B)
        #print electron[i].force
        electron[i].acceleration=electron[i].force/electron[i].mass
        #print electron[i].acceleration
        electron[i].velocity += electron[i].acceleration*dt
        #print electron[i].velocity
        electron[i].pos += electron[i].velocity*dt        
        #print electron[i].pos

