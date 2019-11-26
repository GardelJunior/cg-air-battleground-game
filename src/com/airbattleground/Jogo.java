package com.airbattleground;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import com.airbattleground.jogo.manager.*;
import com.airbattleground.jogo.math.*;

public class Jogo {
	/* Constantes do Jogo */
	public static final int WIDTH = 800, HEIGHT = 600;
	
	/* Usando singleton */
	private static Jogo instance;

	public static Jogo getInstance() {
		if (instance == null) {
			instance = new Jogo();
		}
		return instance;
	}
	
	/* Função Main */
	public static void main(String[] args) {
		Jogo jogo = Jogo.getInstance();
		jogo.initGLFW();
		jogo.initOpenGL();
		jogo.initCamera();
		jogo.initGame();
		jogo.initEvents();
		jogo.mainLoop();
		jogo.terminate();
	}
	
	/* Código do jogo */
	
	private long window;
	private SceneManager sceneManager;
	private boolean isRunning;
	private Mat4 perspectiveMat;
	
	private Jogo() {}
	
	private void initGLFW() {
		if(!glfwInit()) {
			System.err.println("Não foi possível iniciar o GLFW!");
			System.exit(0);
		}
		//Cria as duas janelas, a segunda é ligada a primeira.
		window = glfwCreateWindow(WIDTH, HEIGHT, "Air Battleground", 0, 0);
		
		//Centraliza a janela principal e coloca o minimapa ao lado
		GLFWVidMode monitor = glfwGetVideoMode(glfwGetPrimaryMonitor());
		int centerX = (monitor.width()-WIDTH)/2;
		int centerY = (monitor.height()-HEIGHT)/2;
		glfwSetWindowPos(window, centerX , centerY);
		
		//Diz que o openGL deve desenhar na mainWindow por padrao
		glfwMakeContextCurrent(window);
		glfwFocusWindow(window);
		
		this.isRunning = true;
		
		perspectiveMat = Mat4.getScaleMatrix(5);
	}
	
	private void initOpenGL() {
		//Inicializa o contexto do openGL
		GL.createCapabilities();
		glClearColor(0, 0, 0, 1);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private void initCamera() {
		
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadMatrixf(perspectiveMat.getMatrixAsBuffer());
	}
	
	private void initGame() {
		TextureManager.init();
		FontManager.init();
		FontManager.loadFont("8bitfont", "/8bitfont.fnt");
		sceneManager = SceneManager.getInstance();
	}
	
	private void initEvents() {
		//Evento de tecla pressionada ou solta
		glfwSetKeyCallback(window, (long w, int key, int scancode, int action, int mods) -> {
			sceneManager.onKeyEvent(key, (action > 0)? 1 : 0);
		});
	}
	
	private void mainLoop() {
		//Enquanto a janela principal estiver aberta, faça
		while(!glfwWindowShouldClose(window) && isRunning) {
			glClear(GL_COLOR_BUFFER_BIT);
			glfwPollEvents(); //Atualiza os eventos das duas janelas
			
			//sceneManager.update();
			//sceneManager.render();
			
			glColor3f(1,0,0);
			glBegin(GL_QUADS);
				glVertex2f(10,10);
				glVertex2f(100,10);
				glVertex2f(100,100);
				glVertex2f(10,100);
			glEnd();
			
			glfwSwapBuffers(window);
		}
	}
	
	private void terminate() {
		TextureManager.clear();
		sceneManager.end();
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	public void exitGame() {
		this.isRunning = false;
	}
}
