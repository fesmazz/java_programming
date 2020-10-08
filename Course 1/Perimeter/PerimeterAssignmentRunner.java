import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints = 0;
        for (Point p : s.getPoints()) {
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double avg = 0;
        double sum = getPerimeter(s);
        int numPoints = getNumPoints(s);
        avg = sum / numPoints;
        return avg;
    }

    public double getLargestSide(Shape s) {
        Point prevPt = s.getLastPoint();
        double currDist = 0;
        double bestDist = 0;
        for (Point currPt : s.getPoints()) { 
            currDist = prevPt.distance(currPt);
            if (currDist > bestDist) {
             bestDist = currDist;
        }
            prevPt = currPt;
        }
        return bestDist;
    }

    public double getLargestX(Shape s) {
        Point prevPt = s.getLastPoint();
        double currX = prevPt.getX();
        double bestX = currX;
        for (Point p : s.getPoints()) {
            if (p.getX() > currX) {
                currX = p.getX();
                if (currX > bestX) {
                    bestX = currX;
                }
            }
        }
        return bestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dir = new DirectoryResource();
        double currPer = 0;
        double bestPer = 0;
        for (File f: dir.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if (getPerimeter(s) > currPer) {
                currPer = getPerimeter(s);
                if (currPer > bestPer) {
                    bestPer = currPer;
                }
            }
            
        }
        return bestPer;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dir = new DirectoryResource();
        double currPer = 0;
        double bestPer = 0;
        File bestFile = null;
        for (File f: dir.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if (getPerimeter(s) > currPer) {
                currPer = getPerimeter(s);
                if (currPer > bestPer) {
                    bestPer = currPer;
                    bestFile = f;
                }
            }
            
        }
        return bestFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("numPoints = " + numPoints);
        double avg = getAverageLength(s);
        System.out.println("avg = " + avg);
        double largestSide = getLargestSide(s);
        System.out.println("largestSide = " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("largestX = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largestPerimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String bestFileName = getFileWithLargestPerimeter();
        System.out.println(bestFileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
