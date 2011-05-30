##A program modeling the orbit of the moon around the earth
##Note: Program is not totally accurate as the earth isn't experiencing
##a force from the moon and they are not both orbiting the center of mass

from visual import *
from time import *

G=6.67*10**-11 #m^3/kg*s^2
M=5.97*10**24  #kg
m=1000         #kg
r=3.85*10**8   #m
v=1022         #m/s
t=0            #s
dt=50          #s

Eradius = 6.3*10**6 #m
Mradius = 1.7*10**6 #m

Force = G*m*M/r**2

Earth = sphere(pos=(0,0,0),radius=Eradius*5,color=color.blue)
Moon = sphere(pos=(r,0,0),radius=Mradius*5,color=color.white)

Amoon = norm(Moon.pos)*Force/m
Aearth = norm(Earth.pos)*Force/M
Vmoon = vector(0,v,0)
Vearth = vector(0,0,0)
Smoon = Moon.pos
Searth = Earth.pos

V = (Vmoon.x**2+Vmoon.y**2+Vmoon.z**2)**(1./2.)
kE = (1/2)*m*V**2
gE = -G*m*M/r

kLabel = label(pos=Earth.pos, yoffset=50)
gLabel = label(pos=Earth.pos, yoffset=-50)
tLabel = label(pos=Earth.pos)

print Amoon
print Aearth

while true:
    Amoon = -norm(Moon.pos)*Force/m
    Vmoon += Amoon*dt
    Smoon += Vmoon*dt

    Moon.pos=Smoon
    r = ((Earth.pos.x-Moon.pos.x)**2+(Earth.pos.y-Moon.pos.y)**2+
         (Earth.pos.z-Moon.pos.z)**2)**(1./2.)
    V = (Vmoon.x**2+Vmoon.y**2+Vmoon.z**2)**(1./2.)
    kE = (1./2.)*m*V**2
    gE = -G*m*M/r
    E = kE+gE

    kLabel.text = 'KE = %1.2e Joules' %kE
    gLabel.text = 'GE = %1.2e Joules' %gE
    tLabel.text = 'E = %1.2e Joules' %E

