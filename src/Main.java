//import com.fazecast.jSerialComm.SerialPort.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    
    private JLabel temperaturaLabel;
    private JLabel luzLabel;
    private JLabel umidadeLabel;
    private JButton ligarArCondicionadoButton;
    private JButton ligarLuzButton;
    private JButton ligarUmidificadorButton;

    
    public Main() {
        // Configurações da janela
        setTitle("Sistema de Monitoramento de Sensores");
        setSize(300, 200); // Reduzi o tamanho para remover o botão de atualização
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1)); // 6 linhas, 1 coluna (removi o botão de atualização)

        
        temperaturaLabel = new JLabel("Temperatura: -- °C", SwingConstants.CENTER);
        luzLabel = new JLabel("Luz: --", SwingConstants.CENTER);
        umidadeLabel = new JLabel("Umidade: -- %", SwingConstants.CENTER);
        ligarArCondicionadoButton = new JButton("Ligar Ar Condicionado");
        ligarLuzButton = new JButton("Ligar Luz");
        ligarUmidificadorButton = new JButton("Ligar Umidificador");

        
        add(temperaturaLabel);
        add(luzLabel);
        add(umidadeLabel);
        add(ligarArCondicionadoButton);
        add(ligarLuzButton);
        add(ligarUmidificadorButton);

        // Configurar o Timer para atualizar os dados a cada 3 segundos
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarDados();
            }
        });
        timer.start(); // Inicia o Timer
    }

    
    private void atualizarDados() {
        // Simulação de leitura dos sensores
        double temperatura = Math.random() * 50; // Temperatura entre 0 e 50 °C
        int luz = (int) (Math.random() * 1024);  // Luz entre 0 e 1023
        int umidade = (int) (Math.random() * 100); // Umidade entre 0 e 100%

       
        temperaturaLabel.setText("Temperatura: " + String.format("%.1f", temperatura) + " °C");
        luzLabel.setText("Luz: " + luz);
        umidadeLabel.setText("Umidade: " + umidade + " %");

        // Lógica para ligar/desligar dispositivos
        if (temperatura > 30) {
            ligarArCondicionadoButton.setEnabled(true);
        } else {
            ligarArCondicionadoButton.setEnabled(false);
        }

        if (luz < 500) {
            ligarLuzButton.setEnabled(true);
        } else {
            ligarLuzButton.setEnabled(false);
        }

        if (umidade < 70) { // Supondo que o umidificador ligue quando a umidade for baixa
            ligarUmidificadorButton.setEnabled(true);
        } else {
            ligarUmidificadorButton.setEnabled(false);
        }
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        // Executando a interface gráfica na thread de eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main gui = new Main();
                gui.setVisible(true); // Torna a janela visível
            }
        });
    }
}