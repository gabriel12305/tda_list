package ec.edu.espol.Arbol.Huffman;

public class HuffmanInfo {
    private int code;
    private int frequency;
    private String content;

    public HuffmanInfo(int frequency, String content) {
        this.code = 0;
        this.frequency = frequency;
        this.content = content;
    }

    public int getCode() {
        return code;
    }
    public int getFrequency() {
        return frequency;
    }
    public String getContent() {
        return content;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
