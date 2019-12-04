/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis.wheatHapMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import utils.IOUtils;
import utils.PStringUtils;
import utils.wheat.RefV1Utils;

/**
 *
 * @author feilu
 */
public class GerpAnno {
    
    public GerpAnno () {
        this.splitByChrID();
    }
    
    public void splitByChrID () {
        String wigDirS = "/Volumes/Fei_HDD_Mac/Gerp/asAlle";
        String gerpDirS = "/Users/feilu/Documents/analysisH/vmap2/003_annotation/003_gerp/byChr";
        String header = "Chr\tPos\tGerp";
        File[] fs = new File (wigDirS).listFiles();
        fs = IOUtils.listFilesEndsWith(fs, ".wig");
        List<File> fList = Arrays.asList(fs);
        fList.parallelStream().forEach(f -> {
            int[] chrID = new int[2];
            BufferedWriter[] bws = new BufferedWriter[2]; 
            String chromosome = f.getName().replaceFirst(".gerp.wig", "");
            chrID[0] = RefV1Utils.getChrID(chromosome, 1);
            chrID[1] = chrID[0]+1;
            try {
                for (int i = 0; i < chrID.length; i++) {
                    String outfileS = new File(gerpDirS, "chr"+PStringUtils.getNDigitNumber(3, chrID[i])+"_gerp.txt.gz").getAbsolutePath();
                    bws[i] = IOUtils.getTextGzipWriter(outfileS);
                    bws[i].write(header);
                    bws[i].newLine();
                }
                BufferedReader br = IOUtils.getTextReader(f.getAbsolutePath());
                String temp = null;
                List<String> l = null;
                int chromPos = -1;
                int pos = -1;
                int index = -1;
                StringBuilder sb = new StringBuilder();
                while ((temp = br.readLine()) != null) {
                    if (temp.startsWith("f")) {
                        l = PStringUtils.fastSplitOnWhitespace(temp);
                        chromPos = Integer.parseInt(l.get(2).replaceFirst("start=", ""));
                    }
                    else {
                        sb.setLength(0);
                        pos = RefV1Utils.getPosOnChrID(chromosome, chromPos);
                        index = Arrays.binarySearch(chrID, RefV1Utils.getChrID(chromosome, chromPos));
                        sb.append(chrID[index]).append("\t").append(pos).append("\t").append(temp);
                        bws[index].write(sb.toString());
                        bws[index].newLine();
                        chromPos++;
                    }
                }
                for (int i = 0; i < chrID.length; i++) {
                    bws[i].flush();;
                    bws[i].close();
                }
                br.close();
                System.out.println(f.getAbsolutePath());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
