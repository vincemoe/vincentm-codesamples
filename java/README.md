# Java Samples

## Code Snippets from the app "AR Lucida"

### Description
AR Lucida is a drawing app I wrote a few years ago, and am currently in the process of
refactoring. It emulates a historic drawing tool called the "Camera Obscura", by 
utilizing your phone's camera to superimpose an image over a drawing surface. I have
included a few short snippets of Java code that I use in this program

### Code description

#### ```ColorsFilter.java```
```ColorsFilter.java``` is a class that takes a Bitmap image, and downsamples the 
image to 64 colors. This is useful for creating color images with a limited 
range of pastel colors. It then returns a filtered Bitmap.

#### ```PosterizeFilter.java```
```PosterizeFilter.java``` is a class that takes a Bitmap image, and posterizes it
to a set number of levels (passed in as an argument). This converts the image to 
a defined number of black and white levels. It then returns a filtered Bitmap.

### ```PositionImageHolder.java```
```PositionImageHolder.java``` is a helper class that extends the built in Android 
ImageView class. This class is a custom method of allowing a user to position an image
(location and scale) over the drawing surface before drawing it. 
