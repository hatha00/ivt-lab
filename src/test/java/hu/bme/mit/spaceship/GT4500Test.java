package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  
  private GT4500 ship;
  private TorpedoStore  PrimaryTS;
  private TorpedoStore SecondaryTS; 
;

  @BeforeEach
  public void init(){
    PrimaryTS = mock(TorpedoStore.class);
    SecondaryTS = mock(TorpedoStore.class);  
    this.ship = new GT4500(PrimaryTS, SecondaryTS);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
   
    when(PrimaryTS.fire(1)).thenReturn(true);
    when(PrimaryTS.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(PrimaryTS, times(1)).fire(1); 
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(PrimaryTS.fire(2)).thenReturn(true);
    when(PrimaryTS.isEmpty()).thenReturn(true);
    when(SecondaryTS.fire(12)).thenReturn(true);
    when(SecondaryTS.isEmpty()).thenReturn(false);



    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(PrimaryTS, times(1)).isEmpty();
    verify(SecondaryTS, times(1)).isEmpty(); 
  }

  

}
