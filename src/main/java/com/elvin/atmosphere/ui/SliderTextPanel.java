package com.elvin.atmosphere.ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SliderTextPanel extends JPanel {

    final JSlider slider = new JSlider();
    final JTextField text = new JTextField(4);
    
    public SliderTextPanel(int max, int defaultValue){
        
        slider.setMaximum(max);
        slider.setPreferredSize(new Dimension(200, 20));
        slider.setMinimumSize(new Dimension(200, 20));
        slider.setValue(defaultValue);
        
        
        text.setPreferredSize(new Dimension(40, 20));
        text.setMaximumSize(new Dimension(40, 20));
        text.setText(defaultValue + "");
        
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                text.setText(slider.getValue() + "");
            }
        });
        text.getDocument().addDocumentListener(new DocumentListener() {
            public void removeUpdate(DocumentEvent e) {
                updateSlider();
            }
            public void insertUpdate(DocumentEvent e) {
                updateSlider();
            }
            public void changedUpdate(DocumentEvent e) {
                updateSlider();
            }
            private void updateSlider(){
                
                String textVal = text.getText();
                try{
                    int intVal = Integer.parseInt(textVal);
                    if(intVal <= slider.getMaximum()){
                        slider.setValue(intVal);
                    }
                }catch(Exception e){
                }
            }
        });
        
        this.setLayout(new SpringLayout());
        this.add(slider);
        
        text.setSize(20, 10);
        this.add(text);
        
        SpringUtilities.makeCompactGrid(this, 1, 2, 0, 0, 5, 0);
        
    }
    
    public JSlider getSlider(){
        return slider;
    }
    
    public int getVlue(){
        return slider.getValue();
    }
}
