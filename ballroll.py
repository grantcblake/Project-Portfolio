##A simple program to show a ball rolling with a constant velocity.
##I added a component to have the ball roll backward when it gets near the edge of the screen.


from visual import *

scene=display(title="Virtual MH106",x=0,y=0,width=800,height=600,
              range=vector(6,4,4),center=vector(0,0,0))

ball = sphere(pos=(-5.5,0.16,0),radius=.2,color=color.magenta)
ground = box(pos=(0,-2,0),length=12,height=4,width=4,color=color.green)

a=0 #m/s^2
v=3 #m/s
s=ball.pos.x #m
t=0 #sec
dt=.001 #sec
stotal=0 #sec
velocitylabel = label(pos=(0,-1.5,0))
timelabel = label(pos=(0,-2.5,0))
slabel = label(pos=(0,-3.5,0))

while -5.9<s<5.9:
    rate(2000)

    stotal+=abs(v)*dt
    s+=v*dt
    ball.pos.x=s
    t+=dt
    velocitylabel.text = 'v = %1.3f meters per second'  % v 
    timelabel.text = 't= %1.3f seconds' % t
    slabel.text='s= %1.3f meters' % stotal

    if 5.72 < s < 5.8:
        v = -v
    if -5.8 < s < -5.72:
        v = -v
