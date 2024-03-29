
Variables nash2, x1, x2, x3, ru, u1, u2, u3, u4;
Equation f1,f2,f3,f4, func, func2, func3, u11, u22, u33, u44;

*anion
f1.. u1 =e= exp(x2-x3)/exp(1);
*cation
f2.. u2 =e= exp(x1-x3)/exp(1);
*mixbed
f3.. u3 =e= exp(x1+x2+x3)/exp(3);
*neut
f4.. u4 =e= exp(-x1-x2);
*max function
*log funct
u1.lo=0.0000001;u2.lo=0.0000001;u3.lo=0.0000001;u4.lo=0.0000001;
func.. nash2 =e= log(u1) + log(u2) + log(u3) + log(u4);
func2.. nash2 =g= 0;
func3.. ru =e= ru;

u11.. u1 =G= ru;
u22.. u2 =G= ru;
u33.. u3 =G= ru;
u44.. u4 =G= ru;


x1.lo = 0; x1.up =  1;
*x1.l = -1.2;
x2.lo = 0; x2.up = 1;
*x2.l =  1.0;
x3.lo = 0; x3.up = 1;
*x3.l =  1.0;
ru.lo = 0.001; ru.up = 0.001;

*ru = 0.3;


Model nashcalcconvex / all /;
Solve nashcalcconvex maximize nash2 using nlp;

Display x1.l, x2.l, x3.l,ru.l, nash2.l;
