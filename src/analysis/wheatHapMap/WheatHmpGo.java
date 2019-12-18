/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis.wheatHapMap;

import utils.wheat.RefV1Utils;

/**
 *
 * @author feilu
 */
class WheatHmpGo {
    
    public WheatHmpGo () {
        //this.referenceGenome();
        //this.VMapII();
        //this.geneDB();
        //this.deleteriousDB();
        //this.annotation();
        this.deleteriousBiology();
    }
    
    public void deleteriousBiology () {
        new DeleteriousBiology ();
    }
    
    public void annotation () {
//        this.annoAncestral();
//        this.annoSift();
//        this.annoGerp();
//        this.annoPhyloP();
        //this.annoCrossover();

    }

    public void annoCrossover () {
        new AnnoCrossover();
    }

    public void annoPhyloP() {
        new AnnoPhyloP();
    }
    
    public void annoGerp () {
        new AnnoGerp();
    }
    
    public void annoSift() {
        new AnnoSift();
    }
    
    public void annoAncestral() {
        new AnnoAncestral();
    }
    
    public void deleteriousDB () {
        new DeleteriousDB();
    }
    
    public void geneDB () {
        new GeneDB ();
    }
    
    public void VMapII () {
        new VMapII();
    }
    
    public static void main (String[] args) {
        new WheatHmpGo();
    }
}
