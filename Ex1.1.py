##Models a book falling from rest until it hits the ground

from visual import *

a = -9.8      #m/s^2  acceleration due to gravity
v = 0         #m/s    initial velocity
s = 0.8       #m      initial position
t = 0         #s      current time
dt=.1         #s      interval at which time steps forward

book = box()  #defining book as a box object
book.size = (0.2,0.03,0.21)
book.pos = vector(0,s,0)
book.color = (.01,0,.90)
book.velocity = vector(0,a*t+v,0)

floor = box() #defining floor as a box object for the book to fall to the earth
floor.size = (2,.2,2)
floor.pos = (0,-.1,0)
floor.color  = (.4,.35,0)

print "At", t, "seconds, the book is", s, "meters above the ground."
print "It has a velocity of", v, "meters per second." #print starting values

while s > 0:
    rate(50)
    
    a=a       #the only equation we need to know for kinematics!
    v+=a*dt   #velocity equation derived
    s+=v*dt   #position equation derived
    
    if s < 0: #So that the book will not fall through the floor
        s = 0   
        book.pos.y = s
        booklabel= label(pos=book.pos-(0,.2,0),
                         text='The book hit the ground at %1.2f seconds \nwith a velocity of %1.2f meters per second.' %(t,v),
                         height=10,border=6,font='sans')
        #prints final time and velocity
        break  #exits the loop
    else:
        book.pos.y = s  #moves the book forward (toward the ground)

    t += dt  #increment the time

    print "At", t, "seconds, the book is", s, "meters above the ground."
    print "It has a velocity of", v, "meters per second."
