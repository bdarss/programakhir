package MySketch;
import processing.core.PApplet;

import java.awt.*;

public class ProyekAkhir extends PApplet {
        float sunX, sunY; // Koordinat matahari
        float moonX, moonY; // Koordinat bulan
        int bgColor; // Warna latar belakang
        int jalan;
        int banyakGaris = 16;
        float[] garixX = new float[banyakGaris];
        int banyakPohon = 23;
        float[] pohonX = new float[banyakPohon];


    public static void main(String[] args) {
        String judul = "ProyekAkhir";
        String[] processingArgs = {judul};
        ProyekAkhir mySketch = new ProyekAkhir();
        PApplet.runSketch(processingArgs, mySketch);
    }

        public void settings() {
            size(1366, 768);
        }

        public void setup() {
            sunX = -16;
            sunY = 384;
            moonX = -30;
            moonY = 384;
            bgColor = color(231, 162, 49); // Langit oren ori
            jalan = color(30, 30, 30);
            for (int i = 0; i < banyakGaris; i++) {
                garixX[i] = width - i * 90;
            }
            for (int i = 0; i < banyakPohon; i++) {
                pohonX[i] = width - i * 60; // Setiap objek dimulai dari posisi yang berbeda
            }
        }

        void pohon(float x, float y) {
            noStroke();
            fill(105, 55, 4);
            rect(x-10, y+110, 30, 90);
            fill(11, 102, 35);
            triangle(x - 20, y + 155, x + 5 , y + 105, x + 30, y + 155);
            fill(11, 102, 35);
            triangle(x - 20, y + 140, x + 5, y + 90, x + 30, y + 140);
            fill(11, 102, 35);
            triangle(x - 20, y + 125, x + 5, y + 75, x + 30, y + 125);
        }

        void garisJalan(float x, float y) {
            fill(235,235,235);
            rect(x,540,50,10);
            rect(x,700,50,10);
        }

        void Mobil2() {
            fill(100, 23, 230);
            rect(450, 550, 80, 20); // Gambar badan mobil
            fill(0); // Warna hitam untuk jendela mobil
            rect(460, 530, 60, 20); // Gambar atap mobil
            fill(255,255,255);
            stroke(0,0,0);
            ellipse(520, 570, 10, 10);
            ellipse(460, 570, 10, 10);
        }

        void Mobil1() {
            fill(247, 123, 35);
            rect(100, 500, 80, 20); // Gambar badan mobil
            fill(0); // Warna hitam untuk jendela mobil
            rect(110, 480, 60, 20); // Gambar atap mobil
            fill(255,255,255);
            stroke(0,0,0);
            ellipse(170, 520, 10, 10);
            ellipse(110, 520, 10, 10);
        }

        public void draw() {
            background(bgColor);
            noStroke();
            fill(24, 43, 99);
            rect(0,450,1366,50);

            for (int i = 0; i < banyakPohon; i++) {
                pohon(pohonX[i], 300); // Gambar objek bergerak
                pohonX[i] = (pohonX[i] - 2) % (width + 30); // Perbarui posisi objek

                // Cek apakah objek sudah mencapai sebelah kiri layar
                if (pohonX[i] == - 30) {
                    pohonX[i] = width; // Kembalikan objek ke sebelah kanan layar
                }
            }
            fill(jalan);
            rect(0, 500, 1366, 300);
            for (int i = 0; i < banyakGaris; i++) {
                garisJalan(garixX[i], 300+40); // Gambar objek bergerak
                garixX[i] = (garixX[i] - 2) % (width + 30); // Perbarui posisi objek

                // Cek apakah objek sudah mencapai sebelah kiri layar
                if (garixX[i] == - 50) {
                    garixX[i] = width; // Kembalikan objek ke sebelah kanan layar
                }
            }
            fill(255, 195, 0);
            rect(0,600,1366,10);
            rect(0,650,1366,10);
            fill(104, 203, 90);
            rect(0,610,1366,40);
            Mobil1();
            Mobil2();
            // Menggerakkan matahari dan bulan
            sunX += 2.5;
            sunY -= 1;
            if (sunX >= 683) {
                sunX += 1;
                sunY += 2.5;
            }
            if (sunX >= 1366 && sunY >= 384) {
                moonX += 2.5;
                moonY -= 1;
            }
            if (moonX >= 683) {
                moonX += 1;
                moonY += 2.5;
            }
            if (moonX >= 1366 && moonY >= 384) {
                moonX = -30;
                moonY = 384;
                sunX = -16;
                sunY = 384;
            }
            // Menggambar matahari dan bulan
            drawSun(sunX, sunY);
            drawMoon(moonX, moonY);

            // Mengubah warna latar belakang berdasarkan posisi matahari dan bulan
            updateBackgroundColor();
        }

        // Metode untuk menggambar matahari
        void drawSun(float x, float y) {
            fill(255, 255, 0); // Warna kuning untuk matahari
            ellipse(x, y, 50, 50); // Matahari berbentuk lingkaran
        }

        // Metode untuk menggambar bulan
        void drawMoon(float x, float y) {
            fill(145, 142, 141); // Warna putih untuk bulan
            ellipse(x, y, 50, 50); // Bulan berbentuk lingkaran
        }

        // Metode untuk mengubah warna latar belakang berdasarkan posisi matahari dan bulan
        void updateBackgroundColor() {
            if (sunY < 384) {
                // Siang hari (langit biru)
                bgColor = lerpColor(color(135, 206, 250), color(231, 162, 49), map(sunY, 150, 384, 0, 1));
            } else if (moonY < 384) {
                // Malam hari (langit gelap)
                bgColor = lerpColor(color(0, 0, 0), color(231, 162, 49), map(moonY, 200, 350, 0, 1));
            }
        }
    }

