// FrontEnd Plus GUI for JAD
// DeCompiled : PreejecucionServicio.class

package abad.atae.test.om;

import com.grupobbva.ii.sf.base.BbvaException;
import com.grupobbva.ii.sf.operacion.OperacionMulticanal;
import com.grupobbva.ii.sf.operacion.OperacionMulticanalPrecarga;
import com.ibm.dse.base.*;

import java.io.IOException;
import java.io.PrintStream;

public class PreejecucionServicio extends OperacionMulticanalPrecarga
{

    private boolean esPIBEE;
    private OperacionMulticanal instanceOM;

    public PreejecucionServicio()
    {
    }

    public PreejecucionServicio(String anOperationName)
        throws IOException
    {
        super(anOperationName);
    }

    public PreejecucionServicio(String anOperationName, Context aParentContext)
        throws IOException, DSEInvalidRequestException
    {
        super(anOperationName, aParentContext);
    }

    public PreejecucionServicio(String anOperationName, String aParentContext)
        throws IOException, DSEObjectNotFoundException, DSEInvalidRequestException
    {
        super(anOperationName, aParentContext);
    }
    public OperacionMulticanal crearOM(String nombreOM)
    throws BbvaException
{
    try
    {
        instanceOM = null;
        OperacionMulticanal oM = null;
        Context contextoSesion = Context.getContextNamed("SesionContext");
        oM = (OperacionMulticanal)DSEOperation.readObject(nombreOM);
        setInstanceOM((OperacionMulticanal)DSEOperation.readObject(nombreOM));
        oM.chainTo(contextoSesion);
    }
    catch(Exception e)
    {
        //System.out.println("Error en crarOM() PreejecucionFija" + e.getMessage());
        e.printStackTrace();
    }
    return getInstanceOM();
}
    final void setInstanceOM(OperacionMulticanal aInstaceOM)
    {
        instanceOM = aInstaceOM;
    }

    public final OperacionMulticanal getInstanceOM()
    {
        return instanceOM;
    }
    public final void execute()
        throws BbvaException
    {
        try
        {
        	setValueAt("datosAPP.esPIBEE", "true");
        	
        	ejecutarPrecargaUnica();
        }
        catch(Exception e)
        {
            //System.out.println("Error en execute() PreejecucionServicio" + e.getMessage());
            e.printStackTrace();
            
            throw (BbvaException)e;
        }
        
    }

    public final void executePibee()
        throws BbvaException
    {
        try
        {
            Trace.debug("PIBEE: " + getClass().getName(), "INI executePibee");
            Trace.debug("PIBEE: " + getClass().getName(), "FIN executePibee");
        }
        catch(Exception e)
        {
            Trace.debug("PIBEE: " + getClass().getName(), "Error en executePibee" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void ejecutarPrecargaUnica()
        throws BbvaException
    {
    }

    public void ejecutarPrecargaRepetida()
        throws BbvaException
    {
    }
}