package com.elvin.atmosphere.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.elvin.atmosphere.common.Utils;

public abstract class AbstractMainFrame extends JFrame {

    protected SliderTextPanel tbHeight = new SliderTextPanel(Utils.getScreenDimension().height / 2, 10);
    protected SliderTextPanel lrWidth = new SliderTextPanel(Utils.getScreenDimension().width / 2, 10);
    
    protected SliderTextPanel tbSplit = new SliderTextPanel( 9, 9);
    protected SliderTextPanel lrSplit = new SliderTextPanel( 4, 4);

    protected SliderTextPanel tAdjust = new SliderTextPanel( Utils.getScreenDimension().height / 2, 0);
    protected SliderTextPanel bAdjust = new SliderTextPanel( Utils.getScreenDimension().height / 2, 0);
    protected SliderTextPanel lAdjust = new SliderTextPanel( Utils.getScreenDimension().width / 2, 0);
    protected SliderTextPanel rAdjust = new SliderTextPanel( Utils.getScreenDimension().width / 2, 0);

    protected SliderTextPanel interval = new SliderTextPanel( 1000 * 2, 200);
    
    protected JTextField targetIp = new JTextField(15);
    protected JToggleButton onOffButton = new JToggleButton(); 
    protected JButton redBoxButton = new JButton("RedBox");
    
    protected ColorDemoPanel colorDemoPanel = new ColorDemoPanel();
    
    public void init(){
        
        JPanel top = new JPanel(new SpringLayout());
        
        tbHeight.addSliderValueChangedAction(new SliderValueChangedAction() {
            public void sliderValueChanged(int value) {
                tbHeightSliderValueChanged(value);
            }
        });
        
        lrWidth.addSliderValueChangedAction(new SliderValueChangedAction() {
            public void sliderValueChanged(int value) {
                lrWidthSliderValueChanged(value);
            }
        });
        

        tbSplit.addSliderValueChangedAction(new SliderValueChangedAction() {
            public void sliderValueChanged(int value) {
                tbSplitSliderValueChanged(value);
            }
        });
        

        lrSplit.addSliderValueChangedAction(new SliderValueChangedAction() {
            public void sliderValueChanged(int value) {
                lrSplitSliderValueChanged(value);
            }
        });
        
        tAdjust.addSliderValueChangedAction(new SliderValueChangedAction() {
            public void sliderValueChanged(int value) {
                tAdjustSliderValueChanged(value);
            }
        });

        bAdjust.addSliderValueChangedAction(new SliderValueChangedAction() {
            public void sliderValueChanged(int value) {
                bAdjustSliderValueChanged(value);
            }
        });
        
        lAdjust.addSliderValueChangedAction(new SliderValueChangedAction() {
            public void sliderValueChanged(int value) {
                lAdjustSliderValueChanged(value);
            }
        });

        rAdjust.addSliderValueChangedAction(new SliderValueChangedAction() {
            public void sliderValueChanged(int value) {
                rAdjustSliderValueChanged(value);
            }
        });
        
        interval.addSliderValueChangedAction(new SliderValueChangedAction() {
            public void sliderValueChanged(int value) {
                intervalSliderValueChanged(value);
            }
        });
        
        addLabel(top, "T.B.Height");
        top.add(tbHeight);
        addLabel(top, "L.R.Width");
        top.add(lrWidth);

        addLabel(top, "T.B.Split");
        top.add(tbSplit);
        addLabel(top, "L.R.Split");
        top.add(lrSplit);

        addLabel(top, "T.Adjust");
        top.add(tAdjust);
        addLabel(top, "B.Adjust");
        top.add(bAdjust);
        addLabel(top, "L.Adjust");
        top.add(lAdjust);
        addLabel(top, "R.Adjust");
        top.add(rAdjust);

        addLabel(top, "Interval (ms)");
        top.add(interval);
        SpringUtilities.makeCompactGrid(top, 9, 2, 5, 5, 5, 5);
    
        onOffButton.setText("OFF");
        onOffButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                onOffButtonClicked(e);
            }
        });
        
        redBoxButton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                redBoxButtonReleased(e);
            }
            public void mousePressed(MouseEvent e) {
                redBoxButtonPressed(e);      
            }
        });
        
        
        JPanel bottom = new JPanel(new SpringLayout());
        bottom.add(targetIp);
        bottom.add(onOffButton);
        bottom.add(redBoxButton);
        
        SpringUtilities.makeCompactGrid(bottom, 1, bottom.getComponentCount(), 5, 5, 5, 5);
        
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(top, BorderLayout.NORTH);
        this.getContentPane().add(bottom, BorderLayout.SOUTH);
        this.getContentPane().add(colorDemoPanel, BorderLayout.CENTER);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addLabel(JPanel container, String text){
        JLabel jlabel = new JLabel(text);
        jlabel.setHorizontalAlignment(SwingConstants.RIGHT);
        container.add(jlabel);
    }
    
    protected abstract void onOffButtonClicked(ActionEvent e);

    protected abstract void redBoxButtonPressed(MouseEvent e);

    protected abstract void redBoxButtonReleased(MouseEvent e);
    
    protected abstract void intervalSliderValueChanged(int value) ;

    protected abstract void rAdjustSliderValueChanged(int value);

    protected abstract void lAdjustSliderValueChanged(int value) ;

    protected abstract void bAdjustSliderValueChanged(int value) ;

    protected abstract void tAdjustSliderValueChanged(int value) ;

    protected abstract void lrSplitSliderValueChanged(int value) ;

    protected abstract void tbSplitSliderValueChanged(int value) ;

    protected abstract void lrWidthSliderValueChanged(int value) ;

    protected abstract void tbHeightSliderValueChanged(int value) ;
}



