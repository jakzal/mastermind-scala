import java.io.File

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import infrastructure.filesystem.FilesystemDecodingBoards
import mastermind.DecodingBoards

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[DecodingBoards])
      .to(classOf[FilesystemDecodingBoards])
    bind(classOf[File])
      .annotatedWith(Names.named("decodingBoardsDir"))
      .toInstance(new File("/Users/kuba/Projects/mastermind-scala/data"))
  }
}
