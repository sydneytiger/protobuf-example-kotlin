import com.github.sydneytiger.protobuf.Employee
import com.github.sydneytiger.protobuf.Squad
import com.github.sydneytiger.protobuf.Title
import com.github.sydneytiger.protobuf.Tribe
import com.google.protobuf.util.JsonFormat
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.FileWriter
import java.util.*

fun main() {
    val employeeBuider = Employee.newBuilder();
    val tiger = employeeBuider.setId(10012)
        .setName("Tiger Chen")
        .setTitle(Title.ENGINEER)
        .build();

    val raymond = employeeBuider.setId(10012)
        .setName("Raymond Su")
        .setTitle(Title.ENGINEER)
        .build();

    val dmitriy = employeeBuider.setId(10012)
        .setName("Dmitriy Larin")
        .setTitle(Title.ENGINEER_MANAGER)
        .build();

    // build squad
    val squadBuilder = Squad.newBuilder();
    val totoro = squadBuilder
            .setName("Totoro")
        .addAllMembers(Arrays.asList(tiger, raymond, dmitriy))
        .build();

    // build tribe
    val tribeBuilder = Tribe.newBuilder();
    val a4t = tribeBuilder
            .setName("At4")
        .addSquads(totoro)
        .build();

    println(a4t);

    val binary = a4t.toByteArray();

    // save binary into a file
    val outputStream = FileOutputStream("protocol-buffers-output/tribe_a4t.bin");
    outputStream.write(binary);
    outputStream.close();

    // save as JSON
    val jsonString = JsonFormat.printer()
        .omittingInsignificantWhitespace()
        .print(tribeBuilder)
    println(jsonString)

    val writer = BufferedWriter(FileWriter("protocol-buffers-output/tribe_a4t.json"))
    writer.write(jsonString)
    writer.close()
}
