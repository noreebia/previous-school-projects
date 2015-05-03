function [dx,dy] = jumpingmove(x1,y1,x2,y2,v)

velo = v;
theta = atan(abs(y2-y1)/abs(x2-x1));
g=9.8;

t = 2.5*velo*sin(theta)/g;

time = linspace(0,t,100);


dx = 30*(velo*cos(theta).*time);
if x2<x1
    dx = -dx;
end
dy = 60*(velo*sin(theta)*time-1/2*g*(time.*time));