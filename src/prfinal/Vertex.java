/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prfinal;

/**
 *
 * @author usuario.general
 */
class Vertex {
    final private String id;
    final private String name;
    final private int coordX;
    final private int coordY;


    public Vertex(String id, String name, int cX, int cY) {
            this.id = id;
            this.name = name;
            this.coordX = cX;
            this.coordY = cY;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }
    public String getId() {
            return id;
    }

    public String getName() {
            return name;
    }

    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
    }

    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            Vertex other = (Vertex) obj;
            if (id == null) {
                    if (other.id != null)
                            return false;
            } else if (!id.equals(other.id))
                    return false;
            return true;
    }

    @Override
    public String toString() {
            return name;
    }


}
