package io.github.tomaso2468.renderer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

import io.github.tomaso2468.renderer.math.Vector3f;
import io.github.tomaso2468.renderer.thing.InfinitePlane;
import io.github.tomaso2468.renderer.thing.Sphere;

public class RendererTest {
	public static final int WIDTH = 1920 / 20;
	public static final int HEIGHT = 1080 / 20;
	
	public static void main(String[] args) {
		Scene scene = new Scene();
		
		Sphere sun = new Sphere(new Vector3f(-4, 20, 1), 3);
		sun.emmisive = new Vector3f(95);
		scene.add(sun);
		
		Sphere sphere = new Sphere(new Vector3f(0, 0, -3), 1);
		sphere.emmisive = new Vector3f(0.2, 0.2, 1).mul(1);
		sphere.color = new Vector3f(0, 0, 0);
		scene.add(sphere);
		
		Sphere sphere2 = new Sphere(new Vector3f(-2, 0, -4), 1);
		sphere2.color = new Vector3f(1, 0.2, 0.2);
		sphere2.roughness = 0.5;
		scene.add(sphere2);

		
		Sphere sphere3 = new Sphere(new Vector3f(4, 0, -6), 3);
		sphere3.color = new Vector3f(0.628281,	0.555802,	0.366065);
		sphere3.roughness = 0.4;
		scene.add(sphere3);
		
		InfinitePlane plane = new InfinitePlane(new Vector3f(0, -1, 0));
		plane.color = new Vector3f(0.7);
		plane.roughness = 0.9;
		scene.add(plane);
		
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		AWTRenderer renderer = new AWTRenderer(scene, WIDTH, HEIGHT);
		
		JFrame frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		frame.add(new JComponent() {
			private static final long serialVersionUID = 4406100300560104675L;

			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
			}
			
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(WIDTH, HEIGHT);
			}
		});
		
		frame.pack();
		frame.setVisible(true);
		
		System.out.println("Rendering");
		renderer.render(img, frame);
		System.out.println("Done");
		
		frame.repaint();
	}
}
