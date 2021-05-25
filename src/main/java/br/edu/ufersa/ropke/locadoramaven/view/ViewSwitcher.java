package br.edu.ufersa.ropke.locadoramaven.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewSwitcher {
	private static Map<View, Parent> cache = new HashMap<View, Parent>();
	private static Scene scene;

	public static void setScene(Scene scene) {
		ViewSwitcher.scene = scene;
	}

	public static void switchTo(View view) {
		if (scene == null) {
			System.out.println("A cena nao foi inicializada");
			return;
		}

		try {
			Parent root;

			if (cache.containsKey(view)) {
				System.out.println("Carregando do cache");
				root = cache.get(view);
			} else {
				System.out.println("Carregando do FXML");
				root = FXMLLoader.load(ViewSwitcher.class.getResource(view.getNomeArquivo()));

				cache.put(view, root);
			}

			scene.setRoot(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
