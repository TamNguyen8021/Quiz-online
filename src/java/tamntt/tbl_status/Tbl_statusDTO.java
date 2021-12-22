/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_status;

import java.io.Serializable;

/**
 *
 * @author Tam
 */
public class Tbl_statusDTO implements Serializable {
    private String status;

    public Tbl_statusDTO() {
    }

    public Tbl_statusDTO(String status) {
        this.status = status;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
