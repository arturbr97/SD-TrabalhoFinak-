/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetofinal.util;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author hilton
 */
public class NodeDataTransferModel implements Serializable {

    private String[] keywordsSplitting;
    private String[] textFragment;

    public NodeDataTransferModel(String[] keywordsSplitting, String[] textFragment) {
        this.keywordsSplitting = keywordsSplitting;
        this.textFragment = textFragment;
    }

    public NodeDataTransferModel() {
    }
    
   
    public String[] getKeywordsSplitting() {
        return keywordsSplitting;
    }

    public void setKeywordsSplitting(String[] keywordsSplitting) {
        this.keywordsSplitting = keywordsSplitting;
    }

    public String[] getTextFragment() {
        return textFragment;
    }

    public void setTextFragment(String[] textFragment) {
        this.textFragment = textFragment;
    }

    @Override
    public String toString() {
        return "NodeDataTransferModel{" + "keywordsSplitting=" + keywordsSplitting + ", textFragment=" + textFragment + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Arrays.deepHashCode(this.keywordsSplitting);
        hash = 97 * hash + Arrays.deepHashCode(this.textFragment);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodeDataTransferModel other = (NodeDataTransferModel) obj;
        if (!Arrays.deepEquals(this.keywordsSplitting, other.keywordsSplitting)) {
            return false;
        }
        if (!Arrays.deepEquals(this.textFragment, other.textFragment)) {
            return false;
        }
        return true;
    }

}
