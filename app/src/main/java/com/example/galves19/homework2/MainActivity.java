package com.example.galves19.homework2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

    public class MainActivity extends AppCompatActivity implements View.OnTouchListener, SeekBar.OnSeekBarChangeListener {

        // variables
        private SeekBar redSeekBar, blueSeekBar, greenSeekBar ;
        private EditText currElement;
        private CustomElement selectedElement ;
        CustomSurface mySurface ;


        //@Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            this.mySurface = (CustomSurface)this.findViewById(R.id.customSurface);//my custom SurfaceView!

            mySurface.invalidate();
            mySurface.setOnTouchListener(this);


            redSeekBar = (SeekBar)findViewById(R.id.redSeekBar);
            blueSeekBar = (SeekBar)findViewById(R.id.blueSeekBar);
            greenSeekBar = (SeekBar)findViewById(R.id.greenSeekBar);
            currElement = (EditText)findViewById(R.id.curElement);

            //registering the listeners.
            redSeekBar.setOnSeekBarChangeListener(this);
            blueSeekBar.setOnSeekBarChangeListener(this);
            greenSeekBar.setOnSeekBarChangeListener(this);



        }


        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            //get the coordinates of where the touch happened.
            float touchX = motionEvent.getX();
            float touchY = motionEvent.getY();

            int initRed, initGreen, initBlue;

            /**
             * calls the CustomElement's containsPoint method,
             * selectedElement object becomes recently tapped on element,EditText sets
             * selectedElement, gets RGB values from the selectedElement's color int,
             * sets each seek bar to their corresponding RGB values.
             */
            if(mySurface.lEye.containsPoint((int)touchX, (int)touchY)) {//Left Eye

                this.setSelectedElement(mySurface.lEye);
                currElement.setText(this.getSelectedElement().getName());

                /**
                 External Citation
                 Date: 28 March 2017
                 Resource:
                 https://developer.android.com/reference/android/graphics/Color.html

                 */
                initRed = (selectedElement.getColor() >> 16) & 0xff;
                initGreen = (selectedElement.getColor() >> 8) & 0xff;
                initBlue = (selectedElement.getColor()) & 0xff;

                redSeekBar.setProgress(initRed);
                greenSeekBar.setProgress(initGreen);
                blueSeekBar.setProgress(initBlue);



            }
            if(mySurface.leftEyeInside.containsPoint((int)touchX, (int)touchY)) {//leftEyeInside

                this.setSelectedElement(mySurface.leftEyeInside);
                currElement.setText(this.getSelectedElement().getName());

                initRed = (selectedElement.getColor() >> 16) & 0xff;
                initGreen = (selectedElement.getColor() >> 8) & 0xff;
                initBlue = (selectedElement.getColor()) & 0xff;

                redSeekBar.setProgress(initRed);
                greenSeekBar.setProgress(initGreen);
                blueSeekBar.setProgress(initBlue);

            }
            if(mySurface.rEye.containsPoint((int)touchX, (int)touchY)) {//Right Eye

                this.setSelectedElement(mySurface.rEye);
                currElement.setText(this.getSelectedElement().getName());

                initRed = (selectedElement.getColor() >> 16) & 0xff;
                initGreen = (selectedElement.getColor() >> 8) & 0xff;
                initBlue = (selectedElement.getColor()) & 0xff;

                redSeekBar.setProgress(initRed);
                greenSeekBar.setProgress(initGreen);
                blueSeekBar.setProgress(initBlue);

            }
            if(mySurface.rightEyeInside.containsPoint((int)touchX, (int)touchY)) {//rightEyeInside

                this.setSelectedElement(mySurface.rightEyeInside);
                currElement.setText(this.getSelectedElement().getName());

                initRed = (selectedElement.getColor() >> 16) & 0xff;
                initGreen = (selectedElement.getColor() >> 8) & 0xff;
                initBlue = (selectedElement.getColor()) & 0xff;

                redSeekBar.setProgress(initRed);
                greenSeekBar.setProgress(initGreen);
                blueSeekBar.setProgress(initBlue);

            }
            if(mySurface.nose.containsPoint((int)touchX, (int)touchY)){

                this.setSelectedElement(mySurface.nose);
                currElement.setText(this.getSelectedElement().getName());

                initRed = (selectedElement.getColor() >> 16) & 0xff;
                initGreen = (selectedElement.getColor() >> 8) & 0xff;
                initBlue = (selectedElement.getColor()) & 0xff;

                redSeekBar.setProgress(initRed);
                greenSeekBar.setProgress(initGreen);
                blueSeekBar.setProgress(initBlue);


            }
            if(mySurface.mouth.containsPoint((int)touchX, (int)touchY)) {//Mouth

                this.setSelectedElement(mySurface.mouth);
                currElement.setText(this.getSelectedElement().getName());

                initRed = (selectedElement.getColor() >> 16) & 0xff;
                initGreen = (selectedElement.getColor() >> 8) & 0xff;
                initBlue = (selectedElement.getColor()) & 0xff;

                redSeekBar.setProgress(initRed);
                greenSeekBar.setProgress(initGreen);
                blueSeekBar.setProgress(initBlue);

            }


            mySurface.invalidate();



            return false;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            //This ensures the program doesn't crash when you move a seekBar at the start of the  program!
            if(selectedElement == null) return ;

            int newColor = 0;

            int initRed = (selectedElement.getColor() >> 16) & 0xff;
            int initGreen = (selectedElement.getColor() >> 8) & 0xff;
            int initBlue = (selectedElement.getColor()) & 0xff;

            /**
             * Each if statement checks to see which specific seekBar was changed
             * */
            if(seekBar.equals(redSeekBar)){
                newColor = (255 & 0xff) << 24 | (i & 0xff) << 16 | (initGreen & 0xff) << 8 | (initBlue & 0xff);
            }
            if(seekBar.equals(greenSeekBar)){
                newColor = (255 & 0xff) << 24 | (initRed & 0xff) << 16 | (i & 0xff) << 8 | (initBlue & 0xff) ;
            }
            if(seekBar.equals(blueSeekBar)){
                newColor = (255 & 0xff) << 24 | (initRed & 0xff) << 16 | (initGreen & 0xff) << 8 | (i & 0xff) ;
            }

            selectedElement.setColor(newColor);//to reflect the progress change from the seekBar.

            mySurface.invalidate();



        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {


        }

        /**
         * setters and getters
         *
         */
        public CustomElement getSelectedElement(){
            return this.selectedElement;
        }
        public void setSelectedElement(CustomElement selected){
            this.selectedElement = selected;
        }

    }