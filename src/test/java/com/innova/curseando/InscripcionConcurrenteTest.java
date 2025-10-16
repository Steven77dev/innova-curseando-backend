package com.innova.curseando;

import com.innova.curseando.service.InscripcionService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
public class InscripcionConcurrenteTest {
    private static final Logger logger = LoggerFactory.getLogger(InscripcionConcurrenteTest.class);

    @Autowired
    private InscripcionService inscripcionService;

    @Test
    void pruebaConcurrencia() throws InterruptedException {
        int numHilos = 10;
        ExecutorService executor = Executors.newFixedThreadPool(numHilos);
        CountDownLatch latch = new CountDownLatch(numHilos);
        logger.info("== Inicio ==");
        for (int i = 1; i <= numHilos; i++) {
            final int alumnoNum = i;
            executor.submit(() -> {
                try {
                    // Simular tiempos distintos de llegada
                    Thread.sleep(ThreadLocalRandom.current().nextInt(100, 800));

                    logger.info("Hilo {}  intentando inscribirse...", alumnoNum);

                    var response = inscripcionService.inscribir(
                            "Alumno " + alumnoNum,
                            "alumno" + alumnoNum + "@mail.com",
                            3L
                    );

                    logger.info("Hilo {} Resultado: "+response.getMensaje(),alumnoNum);
                } catch (Exception e) {
                    logger.error("Hilo " + alumnoNum + " Error: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // Espera que terminen todos los hilos
        executor.shutdown();

        logger.info("== Fin ==");
    }
}
