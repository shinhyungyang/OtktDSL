# Build Compiler

- [Main page](../README.md)

You will need the Eclipse IDE for Java and DSL Developers.

## From Eclipse IDE

This was developped using the Eclipse IDE for Java and DSL Developers.

### Build Main Project

1. Import the project into the Eclipse IDE with Maven: Import... > Maven > Existing Maven Projects

2. Select the root directory (`kieker.otel.translation.parent`) and let it scan.

3. In the file tree viewer, right-click on `kieker.otel.translation.parent`.

4. Select `Run As` then `Maven install` and let it build.

### Run Main Project

5. While still in the file tree viewer, right-click `kicker.otel.translation.generation.Main`.

6. Select `Run As` then `Java Application` and let it run.

If you see the followin output, the build was successful:
```
Expected Arguments: <path/to/input.otkt> <path/to/output/folder>
```

### Export Main Project

7. In the file tree viewer, right-click `kieker.otel.translation.generation.Main`.

8. Select Import... > Java > Runnable Jar File.

9. Select the export location and tick `Package required libraries into the generated JAR`.

Multiple attempts may be necessary.

### Use Obtained Jar

Now, you have a functionnal distribution of the Otkt Dsl compiler.
The generated distribution is a runnable jar. You can use it like any Unix script:

```
./<nameOfJar>.jar <path/to/input.otkt> <path/to/output/folder>
```

### Potential issues

 - Using windows WSL, the build will fail if you import the project from the WSL file system but use the Eclipse IDE installed on the windows file system. One possible fix is to install the Git IDE and clone the project directly into the Windows file system. Now that the two elements are on the same "level", things should work as intented.

 - It may be necessary to add the `kieker.otel.translation/target/classes` folder to the classpath of the project: right click on the projet > Properties > Java Build Path > open the Libraries tab > click on ClassPath in the tree viewver > Add Class Folder.