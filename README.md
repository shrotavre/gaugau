# Gaugau
An easy and simple way to solve linear equations using Gauss-Jordan methods in Java.

I found it hard find a Java library to solve linear equations that uses more than 3 variables so then I make one. And actually this is a code used for my 6th term final project of Text Processing subject, then modified and remixed then ta-dah! Uhum.
Click here for more about [Gauss-Jordan](https://en.wikipedia.org/wiki/Gaussian_elimination) elimination method.

# Javadoc

To be released soon. Soon. Really soon.

# Features
- Tiny size addition to your application, only **13kb** for everything
- Operating on array and arraylist for better performance
- No dependency
- Easy to use

# Maven
Still wondering whether should be putted on central repository or not. Hmm..

# Usage
Beforehand, make sure the classes already loaded magnificently in the project. Then follow these 5-step of usage :

First! simply instantiate the Gaugau class.

```java
Gaugau gaugau = new Gaugau();
```

Second! Create the linear equations using float array and LinearEquation class.

```java
// For equation of 2x - y + 5z = 10, create it like this:
float[] coefficients = {2, -1 ,5}; // The coefficients array without the constant.
LinearEquation equation = new LinearEquation(coefficients, 10); // Input the float array and the constant (10)
```

Third! Push all equations to Gaugau instance.

```java
// Suppose you have created 3 equations of the name equation1, equation2, equation3
gaugau.addLinearEquation(equation1);
gaugau.addLinearEquation(equation2);
gaugau.addLinearEquation(equation3);
```

Fourth! Start the elimination process.

```java
gaugau.process(true); // Fill true to rounds the result value. You can left it empty though (default is false)
```

Fifth! Get the coefficient of each variables.

```java
ArrayList<Float> result = gaugau.getResult();
for (int i = 0; i < result.size(); i++) {
    System.out.print("   VAR" + i + " = " + result.get(i));
}
```


# Example
```java
Gaugau gaugau = new Gaugau();

float[] coefficient1 = {2, -2, 1};
float[] coefficient2 = {3, 1, -1};
float[] coefficient3 = {1, -3, 2};

LinearEquation equation1 = new LinearEquation(coefficient1, 3);
LinearEquation equation2 = new LinearEquation(coefficient2, 7);
LinearEquation equation3 = new LinearEquation(coefficient3, 0);

gaugau.addLinearEquation(equation1);
gaugau.addLinearEquation(equation2);
gaugau.addLinearEquation(equation3);
       
gaugau.process(true);
       
ArrayList<Float> result = gaugau.getResult();
for (int i = 0; i < result.size(); i++) {
    System.out.println("VAR" + i + " = " + result.get(i));
}
        
```

