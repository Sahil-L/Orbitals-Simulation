# How to compile and run (Windows)

I added `lib/json-simple-1.1.1.jar` to the project to satisfy the `org.json.simple` imports used in `Space.java`.

- To compile: run `compile.bat` (it uses `lib/*` on the classpath and outputs to `bin`).
- To run: run `run.bat` (includes `--enable-preview` and sets classpath to `bin;lib/*`).

If you prefer Maven or Gradle, use the artifact `com.googlecode.json-simple:json-simple:1.1.1` instead.
