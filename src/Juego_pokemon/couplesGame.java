package Juego_pokemon;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class couplesGame {
    private JFrame frame;
    private List<Pokemon> listaPokemon;
    private int id_carta;
    private int Contador = 0;
    private JToggleButton Boton_aux = new JToggleButton();
    ImageIcon imgCarta = new ImageIcon(getClass().getResource("/img/Carta.jpg"));

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    couplesGame window = new couplesGame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public couplesGame() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(900, 220, 765, 825);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantalla p = new pantalla();
        frame.getContentPane().add(p);
        frame.setTitle("Pokemon couples game");
    }

    private class pantalla extends JPanel {
        private static final long serialVersionUID = 1L;

        public pantalla() {
            setLayout(new BorderLayout(3, 4));
            setVisible(true);
            botones b = new botones();
            add(b, BorderLayout.CENTER);
        }
    }

    private class botones extends JPanel {
        private static final long serialVersionUID = 1L;

        public botones() {
            setLayout(new GridLayout(3, 4));

            cargarListaPokemon();
            // Barajar la lista de Pokémon
            Collections.shuffle(listaPokemon);

            for (Pokemon pokemon : listaPokemon) {
                JToggleButton boton = new JToggleButton();
                boton.setIcon(imgCarta);

                // Obtener el nombre del Pokémon actual
                String nombrePokemon = pokemon.getNombre();

                // Configurar la imagen seleccionada para el botón
                Image reescalado = new ImageIcon(getClass().getResource("/img/" + nombrePokemon + ".png"))
                        .getImage().getScaledInstance(189, 266, java.awt.Image.SCALE_SMOOTH);
                boton.setSelectedIcon(new ImageIcon(reescalado));

                // Configurar ActionListener para procesar la carta
                boton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
							procesarCarta(boton, pokemon);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
                    }
                });

                add(boton);
            }
        }
    }

    private void cargarListaPokemon() {
        listaPokemon = new ArrayList<>();
        listaPokemon.add(new Pokemon(1, "Alakazam"));
        listaPokemon.add(new Pokemon(2, "Dragonite"));
        listaPokemon.add(new Pokemon(3, "Gengar"));
        listaPokemon.add(new Pokemon(4, "Gyarados"));
        listaPokemon.add(new Pokemon(5, "Machamp"));
        listaPokemon.add(new Pokemon(6, "Snorlax"));

        // Duplicar la lista para formar las parejas
        List<Pokemon> listaDuplicada = new ArrayList<>(listaPokemon);
        listaPokemon.addAll(listaDuplicada);
    }

    public void procesarCarta(JToggleButton Imagen, Pokemon pokemon) throws InterruptedException {      
    	if (Contador == 0) {
            Contador++;
            id_carta = pokemon.getId();
            Boton_aux = Imagen;
        } else {
            Contador = 0;

            if (id_carta != pokemon.getId()) { // If you fail this happens //
            	Thread.sleep(500);
                Imagen.setSelected(false);
                Boton_aux.setSelected(false);
                
            } else { // If you get it right this happens //
            	Thread.sleep(1000);
                Imagen.setEnabled(false);
                Boton_aux.setEnabled(false);
            }
        }
    }

    public static class Pokemon {
        private int id;
        private String nombre;

        public Pokemon(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }
    }
}
