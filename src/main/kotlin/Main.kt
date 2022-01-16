import com.github.sydneytiger.protobuf.Employee
import com.github.sydneytiger.protobuf.Title
import com.google.protobuf.util.JsonFormat
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.FileWriter


fun main(args: Array<String>) {
    val employeeBuilder = Employee.newBuilder()

    println("Building employee Tiger Chen");
    val tiger = employeeBuilder.setId(10012)
        .setName("Tiger Chen")
        .setTitle(Title.ENGINEER)
        .build();

    println(tiger)

    // serialise to binary. The binary can be sent over internet or save into a file
    // it is language natural, platform natural
    val binary = tiger.toByteArray()

    // save binary into a file
    val outputStream = FileOutputStream("protocol-buffers-output/employee_tiger.bin")
    outputStream.write(binary)
    outputStream.close()

    // save as JSON
    val jsonString = JsonFormat.printer()
        .omittingInsignificantWhitespace()
        .print(employeeBuilder)
    println(jsonString)

    val writer = BufferedWriter(FileWriter("protocol-buffers-output/employee_tiger.json"))
    writer.write(jsonString)
    writer.close()

    // Deserialise binary into Employee object
    println("Deserialise binary into an Employee object")
    val anotherTiger = Employee.parseFrom(binary)
    println(anotherTiger)
}