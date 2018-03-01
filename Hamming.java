private static void encode() {
        //порождающая матрица, представленная для удобства в виде чисел
        //отражающих значение битовой комбинации столбца
        int[] matrix = {11, 14, 7, 8, 4, 2, 1};
        //строка вывода результирующей цепочки
        String result = "";
        for (int i = 0; i < inputBytes.length; i++) {
            //берем из байта первые 4 бита, выполняя сдвиг на 4
            int four = inputBytes[i] >> 4;
            //для каждого столбца матрицы
            for (int j = 0; j < matrix.length; j++) {
                //записываем в результат полученный бит
                result += codeBit(four, matrix[j]);
            }
            //берем последние четыре бита остатком от деления на 8
            four = inputBytes[i] % (int) Math.pow(2, 4);
            //для каждого столбца матрицы
            for (int j = 0; j < matrix.length; j++) {
                //записываем в результат полученный бит
                result += codeBit(four, matrix[j]);
            }
        }
        //выделяем память под массив исходящих байт
        outputBytes = new byte[result.length() / 8];
        //до конца цепочки
        for (int i = 0; i < outputBytes.length; i++) {
            //получаем значение байта из 8 символов строки резултата
            outputBytes[i] = (byte) Integer.parseInt(result.substring(i * 8, (i * 8) + 8), 2);
        }
    }

    private static int codeBit(int a, int b) {
        //обнуляем исходящий бит
        int codeBit = 0;
        //выполняем побитовый and
        int product = a & b;
        //получим длину строки
        int length = Integer.toBinaryString(product).length();
        //для 4х бит
        for (int i = 0; i < length; i++) {
            //берем остаток от деления на два (младший бит)
            //и выполняем XOR с исходящим битом
            codeBit ^= product % 2;
            //cдвигаем 4ку бит на 1
            product >>= 1;
        }
        return codeBit;
    }