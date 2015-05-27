package exemplos;

import java.io.IOException;

import org.jtrace.Materials;
import org.jtrace.Scene;
import org.jtrace.Tracer;
import org.jtrace.ViewPlane;
import org.jtrace.cameras.Camera;
import org.jtrace.cameras.PinHoleCamera;
import org.jtrace.geometry.Plane;
import org.jtrace.geometry.Sphere;
import org.jtrace.interceptor.ShadowInterceptor;
import org.jtrace.lights.Light;
import org.jtrace.lights.PointLight;
import org.jtrace.listeners.ImageListener;
import org.jtrace.primitives.ColorRGB;
import org.jtrace.primitives.Point3D;
import org.jtrace.primitives.Vector3D;
import org.jtrace.shader.Shaders;

public class DojoJTrace
{
	
	public static void main(String[] args) throws IOException
	{
		Scene cena = new Scene();
		Camera camera = new PinHoleCamera(new Point3D(0, 0, -20), Point3D.ORIGIN, Vector3D.UNIT_Y);
		cena.setCamera(camera);
		camera.setZoomFactor(5);
		
		ViewPlane vp = new ViewPlane(600, 400);
		Tracer tracer = new Tracer();
		
		Sphere objeto = new Sphere(Point3D.ORIGIN,10, Materials.matte(ColorRGB.WHITE));
		cena.add(objeto);
		cena.add(new PointLight( new Point3D(-10,20,-10)));
		
		Plane plano = new Plane(new Point3D(0, -10, 0), Vector3D.UNIT_Y, Materials.matte(ColorRGB.WHITE));
		cena.add(plano);
		
		tracer.addInterceptors(new ShadowInterceptor());
		
		tracer.addShaders(Shaders.ambientShader());
		tracer.addShaders(Shaders.diffuseShader());
		tracer.addListeners(new ImageListener("imagem.png","png"));
		tracer.render(cena, vp);
		
		
	}
}
