##Finds the maximum height of an object thrown from the ground with an
##initial velocity

from visual import *

scene=display(title="Exercise 2.3",x=0,y=0,width=800,height=600,
              range=vector(10,10,4),center=vector(10,10,0))

Sox = 0.0; Soy = 2.0; Soz = 0.0   #m
Vox = 0.0; Voy = 6.2; Voz = 0.0   #m/s
Aox = 0.0; Aoy =-9.8; Aoz = 0.0   #m/s^2
t   = 0.0; dt  = 0.001            #s

Smax = Soy

S = vector(Sox,Soy,Soz)
V = vector(Vox,Voy,Voz)
A = vector(Aox,Aoy,Aoz)

while V.y >= 0:
    rate (1/dt)
    
    V += A*dt
    S += V*dt

    if S.y > Smax:
        Smax = S.y
    
print 'Max Position = %1.5f m.' %Smax

