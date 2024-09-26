package generic.test.ex3;

import generic.test.ex3.unit.BioUnit;
import generic.test.ex3.unit.Shuttle;

public class UnitPrinter {

    static <T extends BioUnit> void printV1(Shuttle<T> shuttle) {
        T unit = shuttle.out();
        System.out.println(unit.toString());
    }

    static void printV2(Shuttle<? extends BioUnit> shuttle) {
        BioUnit unit = shuttle.out();
        System.out.println(unit.toString());
    }
}
