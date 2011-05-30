##A program to model the motion of an object as it flies through the air
##after being launched from a projectile launcher to calculate where
##to put a cup where the ball will land.

from visual import *

scene=display(title="Cannon",x=0,y=0,width=800,height=600,
              range=vector(4,2,2),center=vector(0,2,0))

Sox = 0.0; Soy = 0.978; Soz = 0.0   #m
Vox = 4.734; Voy = 0.0; Voz = 0.0   #m/s
Aox = 0.0; Aoy =-9.8; Aoz = 0.0     #m/s^2
t   = 0.0; dt  = 0.01               #s

S = vector(Sox,Soy,Soz)
V = vector(Vox,Voy,Voz)
A = vector(Aox,Aoy,Aoz)

projectile = sphere(pos=S,radius=0.012,color=(0.6,0,0.8))         #dark purple
ground = box(pos=(0,-1,0),size=(16,1.6,4),color=(0.9,0.6,0.2))  #beautiful brown
cup = cylinder(pos=(1.95,0.05,0),axis=(0,0.1,0),radius=0.05,color=color.red)
hoop1 = ring(pos=(0.5,0.924,0),radius=0.1,color=color.red)
hoop2 = ring(pos=(0.75,0.845,0),radius=0.1,color=color.white)
hoop3 = ring(pos=(1.0,0.752,0),radius=0.1,color=color.blue)

count=0 #variable to make the tracker dot to appear 

while S.y >= 0:
    V += A*dt
    S += V*dt
    t += dt
    
    projectile.pos=S
    if count%1==0:
        tracker = sphere(pos=S,radius=0.01)

    if 0.47 < S.x < 0.52:
        print 'Sx = %1.3f m.' %S.x, 'Sy = %1.3f m.' %S.y
        print 'Vx = %1.3f m/s.' %V.x, 'Vy = %1.3f m/s.' %V.y
        print 'Ax = %1.3f m/s/s.' %A.x, 'Ay = %1.3f m/s/s.' %A.y
        print ' '

    if 0.72 < S.x < 0.78:
        print 'Sx = %1.3f m.' %S.x, 'Sy = %1.3f m.' %S.y
        print 'Vx = %1.3f m/s.' %V.x, 'Vy = %1.3f m/s.' %V.y
        print 'Ax = %1.3f m/s/s.' %A.x, 'Ay = %1.3f m/s/s.' %A.y
        print ' '

    if 0.97 < S.x < 1.03:
        print 'Sx = %1.3f m.' %S.x, 'Sy = %1.3f m.' %S.y
        print 'Vx = %1.3f m/s.' %V.x, 'Vy = %1.3f m/s.' %V.y
        print 'Ax = %1.3f m/s/s.' %A.x, 'Ay = %1.3f m/s/s.' %A.y
        print ' '

    if  S.y < 0.15:
        print 'Sx = %1.3f m.' %S.x, 'Sy = %1.3f m.' %S.y
        print 'Vx = %1.3f m/s.' %V.x, 'Vy = %1.3f m/s.' %V.y
        print 'Ax = %1.3f m/s/s.' %A.x, 'Ay = %1.3f m/s/s.' %A.y
        print ' '

    count += 1
