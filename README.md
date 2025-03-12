# Minimum Bounding Box with Rotating Calipers in Java

This Java application allows users to interactively create points by clicking on the screen, calculate and visualize the convex hull, bounding box, and minimum bounding box (OMBB) for the set of points using the Rotating Calipers algorithm. The program also demonstrates how the Rotating Calipers method can efficiently compute the optimal minimum bounding box of a set of points.

## Features

- **Interactive Point Creation**: Add points to the canvas by clicking on the screen.
- **Convex Hull Calculation**: Automatically computes the convex hull of the points.
- **Bounding Box Calculation**: Computes the axis-aligned bounding box (AABB) and the optimal minimum bounding box (OMBB) using Rotating Calipers.
- **Rotating Calipers Algorithm**: Efficiently computes the minimum bounding box by rotating the supporting lines across the convex hull.
- **Real-Time Updates**: As new points are added, the convex hull and bounding box are dynamically updated.

## Code Structure

The main components of the program are:

- **Main.java**: The entry point for the program and the GUI.
- **ConvexHull.java**: Contains the logic for computing the convex hull.
- **BoundingBox.java**: Handles the computation of the bounding box and minimum bounding box (OMBB).
- **RotatingCaliper.java**: Implements the Rotating Calipers algorithm for calculating the minimum bounding box.
- **Point.java**: A class that represents the points used in the calculation.

## Algorithm Overview

The **Rotating Calipers** algorithm calculates the minimum bounding box for a set of points. This is made possible by a theorem by Shapira, which states that the smallest-area enclosing rectangle of a polygon has a side collinear with one of the edges of its convex hull. The Rotating Calipers algorithm iterates over the edges of the convex hull, rotates them, and calculates the bounding box for each rotation. The smallest bounding box found during this process is returned as the minimum bounding box.

### Rotating Calipers Algorithm Steps:

1. **Generate a Convex Hull** from a set of points.
2. **Find the extreme points** of the convex hull.
3. **Construct Supporting Lines** at the minimum and maximum x, y coordinates.
4. **Set Initial Rectangle Area** to infinity.
5. **Rotate Supporting Lines** to align with each edge of the convex hull, calculating the area of the bounding box for each rotation.
6. **Repeat** for all edges of the convex hull.
7. **Return the rectangle with the smallest area**.

### Performance

The **Rotating Calipers** algorithm operates in linear time (O(m)) relative to the number of edges of the convex hull. However, it is bound by the convex hull generation, which typically takes O(n log n) time. Thus, the total complexity is O(n log n) for generating the convex hull, followed by O(m) for calculating the minimum bounding box, making it efficient for large datasets.

## References

- [Rotating Calipers Algorithm on GitHub](https://github.com/Glissando/Rotating-Calipers.git)
- [Shapira's Theorem](https://www.iis.sinica.edu.tw/papers/liu/21474-F.pdf)
- [Rotating Calipers Paper (CiteSeer)](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.155.5671&rep=rep1&type=pdf)

