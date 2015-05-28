import org.jtrace.Material;
import org.jtrace.Materials;
import org.jtrace.Scene;
import org.jtrace.cameras.Camera;
import org.jtrace.cameras.PinHoleCamera;
import org.jtrace.geometry.Plane;
import org.jtrace.geometry.Sphere;
import org.jtrace.lights.Light;
import org.jtrace.lights.PointLight;
import org.jtrace.primitives.ColorRGB;
import org.jtrace.primitives.Point3D;
import org.jtrace.primitives.ReflectanceCoefficient;
import org.jtrace.primitives.Vector3D;

import exemplos.MainWindow;


public class Main
{
	private static Window window = new Window(createScene());
	
	public static void main(String[] args){
		window.setVisible(true);
		
	}
	
	public static Scene createScene()
	{
		Point3D lookAt = new Point3D(0, 0, 0);
        Point3D eye = new Point3D(0, 5, -100);
        Vector3D up = Vector3D.UNIT_Y;
        
        double sphereRadius = 7.5;
        
        Point3D centerRed = new Point3D(-10.0, 0, 0);
        Point3D centerYellow = new Point3D(10.0, 0, 0);
        
        Point3D lightPos = new Point3D(-100, 200, -90);
        
        Point3D planePoint = new Point3D(0, -sphereRadius, 0);
        Vector3D planeNormal = Vector3D.UNIT_Y;
        
        Sphere reddie = new Sphere(centerRed, sphereRadius, Materials.metallic(ColorRGB.RED));
        Sphere yellowie = new Sphere(centerYellow, sphereRadius, Materials.metallic(ColorRGB.YELLOW));
        
        Plane plane = new Plane(planePoint, planeNormal, Materials.matte(ColorRGB.WHITE));
        
        Light light = new PointLight(lightPos);
        
        
        Camera pinHoleCamera = new PinHoleCamera(eye, lookAt, up);
        pinHoleCamera.setZoomFactor(10);
        
        return new Scene().add(reddie, yellowie, plane).add(light).setCamera(pinHoleCamera);
        
	}
	
}