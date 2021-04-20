package src;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class M8PrintStream extends PrintStream {
	private final PrintStream ps;
	public static boolean wrote;
	
	M8PrintStream(OutputStream fos, PrintStream ps) {
		super(fos);
		this.ps = ps;
		wrote = false;
	}
	
	@Override
    public void close() {
        // just for documentation
        super.close();
    }

    @Override
    public void flush() {
        super.flush();
        ps.flush();
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        super.write(buf, off, len);
        ps.write(buf, off, len);
        wrote = true;
    }

    @Override
    public void write(int b) {
        super.write(b);
        ps.write(b);
        wrote = true;
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
        ps.write(b);
        wrote = true;
    }

}
