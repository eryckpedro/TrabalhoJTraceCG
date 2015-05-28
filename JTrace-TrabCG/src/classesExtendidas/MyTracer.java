package classesExtendidas;

import org.jtrace.Jay;
import org.jtrace.Scene;
import org.jtrace.Tracer;
import org.jtrace.ViewPlane;
import org.jtrace.cameras.Camera;
import org.jtrace.primitives.ColorRGB;

public class MyTracer extends Tracer {
	
	public void render(Scene scene, ViewPlane viewPlane)
	{
		final int hres = viewPlane.getHres();
        final int vres = viewPlane.getVres();
        final Camera camera = scene.getCamera();

        fireStart(viewPlane);
        initInterceptors(scene);
        
        for (int r = 0; r < vres; r++) {
            for (int c = 0; c < hres; c++) {
                final Jay jay = camera.createJay(r, c, vres, hres);

                final ColorRGB color = trace(scene, jay);

                fireAfterTrace(color, c, r);
            }
        }

        fireFinish();
	}

}
