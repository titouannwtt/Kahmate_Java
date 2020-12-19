SRC_DIR := src/
BIN_DIR := bin/
JAVADOC_DIR := javadoc/
JUNIT_JAR := libs/junit4.jar
HAMCREST_JAR := libs/hamcrest.jar


#packages
MODEL_DIR := $(SRC_DIR)model/
API_DIR := $(SRC_DIR)api/
CONTROLLER_DIR := $(SRC_DIR)controller/
UI_DIR := $(SRC_DIR)ui/
HELPER_DIR := $(SRC_DIR)helper/
ENUM_DIR := $(SRC_DIR)enums/
ACTIONS_DIR := $(SRC_DIR)actions/
UNDO_REDO_DIR := $(SRC_DIR)undo/

#sub packages
CONTAINER_UI_DIR = $(UI_DIR)container/
CONTAINER_CONTROLLER_DIR := $(CONTROLLER_DIR)container/
POPUP_UI_DIR := $(UI_DIR)popup/
POPUP_CONTROLLER_DIR := $(CONTROLLER_DIR)popup/


FLAGS := -d $(BIN_DIR) -sourcepath $(SRC_DIR) -classpath $(BIN_DIR)
JC := javac
JEXE := java -classpath "$(BIN_DIR):$(JUNIT_JAR):$(HAMCREST_JAR)"
JDOC := javadoc
RM := rm -rf 
MKDIR := mkdir

all: $(BIN_DIR) $(JAVADOC_DIR) $(BIN_DIR)Application.class doc

$(BIN_DIR):
	$(MKDIR) $(BIN_DIR)


#application
$(BIN_DIR)Application.class: $(SRC_DIR)Application.java $(BIN_DIR)Window.class
	$(JC) $(FLAGS) $(SRC_DIR)Application.java


#models

$(BIN_DIR)Ball.class: $(MODEL_DIR)Ball.java $(BIN_DIR)Position.class $(BIN_DIR)Person.class	
	$(JC) $(FLAGS) $(MODEL_DIR)Ball.java

$(BIN_DIR)BoardModel.class: $(MODEL_DIR)BoardModel.java $(BIN_DIR)TeamType.class $(BIN_DIR)Cell.class $(BIN_DIR)Player.class \
							$(BIN_DIR)Person.class $(BIN_DIR)OrdinaryPerson.class $(BIN_DIR)SmartPerson.class \
							$(BIN_DIR)HardPerson.class $(BIN_DIR)FastPerson.class $(BIN_DIR)BeefyPerson.class \
							$(BIN_DIR)Cell.class $(BIN_DIR)Assert.class $(BIN_DIR)BoardState.class $(BIN_DIR)BoardView.class \
							$(BIN_DIR)RandomCard.class $(BIN_DIR)Ball.class $(BIN_DIR)ModelBoolean.class \
							$(BIN_DIR)MoveAction.class $(BIN_DIR)PassAction.class $(BIN_DIR)PlatingAction.class $(BIN_DIR)UndoRedo.class \
							$(BIN_DIR)BoardSave.class $(BIN_DIR)GoalCell.class $(BIN_DIR)ShotAction.class
	$(JC) $(FLAGS) $(MODEL_DIR)BoardModel.java

$(BIN_DIR)BeefyPerson.class: $(MODEL_DIR)BeefyPerson.java $(BIN_DIR)Person.class 
	$(JC) $(FLAGS) $(MODEL_DIR)BeefyPerson.java

$(BIN_DIR)Card.class: $(MODEL_DIR)Card.java
	$(JC) $(FLAGS) $(MODEL_DIR)Card.java

$(BIN_DIR)CellState.class: $(MODEL_DIR)CellState.java
	$(JC) $(FLAGS) $(MODEL_DIR)CellState.java

$(BIN_DIR)FastPerson.class: $(MODEL_DIR)FastPerson.java $(BIN_DIR)Person.class 
	$(JC) $(FLAGS) $(MODEL_DIR)FastPerson.java

$(BIN_DIR)Hand.class: $(MODEL_DIR)Hand.java $(BIN_DIR)Card.class $(BIN_DIR)RandomCard.class
	$(JC) $(FLAGS) $(MODEL_DIR)Hand.java

$(BIN_DIR)HardPerson.class: $(MODEL_DIR)HardPerson.java $(BIN_DIR)Person.class 
	$(JC) $(FLAGS) $(MODEL_DIR)HardPerson.java

$(BIN_DIR)OrdinaryPerson.class: $(MODEL_DIR)OrdinaryPerson.java $(BIN_DIR)Person.class 
	$(JC) $(FLAGS) $(MODEL_DIR)OrdinaryPerson.java

$(BIN_DIR)Person.class: $(MODEL_DIR)Person.java $(BIN_DIR)Position.class $(BIN_DIR)PersonState.class  \
						$(BIN_DIR)Cell.class $(BIN_DIR)Console.class $(BIN_DIR)Distance.class $(BIN_DIR)BoardView.class \
						$(BIN_DIR)GoalCell.class
	$(JC) $(FLAGS) $(MODEL_DIR)Person.java

$(BIN_DIR)PersonState.class: $(MODEL_DIR)PersonState.java $(BIN_DIR)TeamType.class
	$(JC) $(FLAGS) $(MODEL_DIR)PersonState.java

$(BIN_DIR)Player.class: $(MODEL_DIR)Player.java $(BIN_DIR)PlayerState.class $(BIN_DIR)TeamType.class $(BIN_DIR)Card.class \
						$(BIN_DIR)Person.class $(BIN_DIR)Hand.class $(BIN_DIR)Assert.class $(BIN_DIR)Position.class
	$(JC) $(FLAGS) $(MODEL_DIR)Player.java 

$(BIN_DIR)PlayerState.class: $(MODEL_DIR)PlayerState.java 
	$(JC) $(FLAGS) $(MODEL_DIR)PlayerState.java 

$(BIN_DIR)SmartPerson.class: $(MODEL_DIR)SmartPerson.java $(BIN_DIR)Person.class 
	$(JC) $(FLAGS) $(MODEL_DIR)SmartPerson.java

#enums
$(BIN_DIR)TeamType.class: $(ENUM_DIR)TeamType.java
	$(JC) $(FLAGS) $(ENUM_DIR)TeamType.java

$(BIN_DIR)GameState.class: $(ENUM_DIR)GameState.java 
	$(JC) $(FLAGS) $(ENUM_DIR)GameState.java

$(BIN_DIR)BoardState.class: $(ENUM_DIR)BoardState.java 
	$(JC) $(FLAGS) $(ENUM_DIR)BoardState.java

#controllers
$(BIN_DIR)BoardController.class: $(CONTROLLER_DIR)BoardController.java $(BIN_DIR)Cell.class $(BIN_DIR)Console.class $(BIN_DIR)Assert.class \
								 $(BIN_DIR)BoardView.class $(BIN_DIR)BoardModel.class $(BIN_DIR)Assert.class $(BIN_DIR)BoardState.class
	$(JC) $(FLAGS) $(CONTROLLER_DIR)BoardController.java


$(BIN_DIR)ChoiceController.class: $(CONTAINER_CONTROLLER_DIR)ChoiceController.java $(BIN_DIR)ChoiceContainer.class  \
								  $(BIN_DIR)UndoRedo.class $(BIN_DIR)BoardModel.class $(BIN_DIR)BoardSave.class
	$(JC) $(FLAGS) $(CONTAINER_CONTROLLER_DIR)ChoiceController.java

$(BIN_DIR)CreditController.class: $(CONTROLLER_DIR)CreditController.java $(BIN_DIR)Window.class $(BIN_DIR)CreditView.class
	$(JC) $(FLAGS) $(CONTROLLER_DIR)CreditController.java

$(BIN_DIR)EndGameController.class: $(CONTROLLER_DIR)EndGameController.java $(BIN_DIR)Window.class $(BIN_DIR)EndGameView.class \
								   $(BIN_DIR)GameState.class
	$(JC) $(FLAGS) $(CONTROLLER_DIR)EndGameController.java

$(BIN_DIR)HomeController.class: $(CONTROLLER_DIR)HomeController.java $(BIN_DIR)HomeView.class $(BIN_DIR)Window.class
	$(JC) $(FLAGS) $(CONTROLLER_DIR)HomeController.java

$(BIN_DIR)SelectionPlayerController.class: $(CONTAINER_CONTROLLER_DIR)SelectionPlayerController.java $(BIN_DIR)SelectionPlayerContainer.class \
										   $(BIN_DIR)BoardModel.class
	$(JC) $(FLAGS) $(CONTAINER_CONTROLLER_DIR)SelectionPlayerController.java

$(BIN_DIR)StartGameController.class: $(CONTAINER_CONTROLLER_DIR)StartGameController.java $(BIN_DIR)StartGameContainer.class $(BIN_DIR)BoardView.class
	$(JC) $(FLAGS) $(CONTAINER_CONTROLLER_DIR)StartGameController.java

#ui (views)

$(BIN_DIR)BoardView.class: $(UI_DIR)BoardView.java $(BIN_DIR)BoardModel.class $(BIN_DIR)Cell.class $(BIN_DIR)BoardController.class $(BIN_DIR)GoalCell.class $(BIN_DIR)Position.class $(BIN_DIR)Palette.class \
						   $(BIN_DIR)Player.class $(BIN_DIR)Person.class $(BIN_DIR)OrdinaryPerson.class $(BIN_DIR)FastPerson.class \
						   $(BIN_DIR)HardPerson.class $(BIN_DIR)SmartPerson.class $(BIN_DIR)TeamType.class $(BIN_DIR)Console.class \
						   $(BIN_DIR)Container.class $(BIN_DIR)StartGameContainer.class $(BIN_DIR)Position.class $(BIN_DIR)Distance.class \
						   $(BIN_DIR)SelectionPlayerContainer.class $(BIN_DIR)ChoiceContainer.class $(BIN_DIR)Window.class $(BIN_DIR)EndGameView.class
	$(JC) $(FLAGS) $(UI_DIR)BoardView.java

$(BIN_DIR)Cell.class: $(UI_DIR)Cell.java $(BIN_DIR)Position.class $(BIN_DIR)CellState.class $(BIN_DIR)Person.class $(BIN_DIR)Assert.class \
					  $(BIN_DIR)Palette.class $(BIN_DIR)Ball.class
	$(JC) $(FLAGS) $(UI_DIR)Cell.java

$(BIN_DIR)ChoiceCardPopUp.class: $(POPUP_UI_DIR)ChoiceCardPopUp.java $(BIN_DIR)Card.class $(BIN_DIR)BoardModel.class $(BIN_DIR)Player.class \
								 $(BIN_DIR)Position.class $(BIN_DIR)Cell.class
	$(JC) $(FLAGS) $(POPUP_UI_DIR)ChoiceCardPopUp.java

$(BIN_DIR)ConfirmShotPopUp.class: $(POPUP_UI_DIR)ConfirmShotPopUp.java 
	$(JC) $(FLAGS) $(POPUP_UI_DIR)ConfirmShotPopUp.java

$(BIN_DIR)CreditView.class: $(UI_DIR)CreditView.java $(BIN_DIR)CreditController.class
	$(JC) $(FLAGS) $(UI_DIR)CreditView.java

$(BIN_DIR)CustomButton.class: $(UI_DIR)CustomButton.java $(BIN_DIR)Palette.class $(BIN_DIR)Console.class
	$(JC) $(FLAGS) $(UI_DIR)CustomButton.java

$(BIN_DIR)Container.class: $(CONTAINER_UI_DIR)Container.java $(BIN_DIR)Window.class
	$(JC) $(FLAGS) $(CONTAINER_UI_DIR)Container.java

$(BIN_DIR)ChoiceContainer.class: $(CONTAINER_UI_DIR)ChoiceContainer.java $(BIN_DIR)ChoiceController.class $(BIN_DIR)BoardModel.class
	$(JC) $(FLAGS) $(CONTAINER_UI_DIR)ChoiceContainer.java

$(BIN_DIR)EndGameView.class: $(UI_DIR)EndGameView.java $(BIN_DIR)Window.class $(BIN_DIR)EndGameController.class
	$(JC) $(FLAGS) $(UI_DIR)EndGameView.java

$(BIN_DIR)GoalCell.class: $(UI_DIR)GoalCell.java $(BIN_DIR)TeamType.class
	$(JC) $(FLAGS) $(UI_DIR)GoalCell.java

$(BIN_DIR)HomeView.class: $(UI_DIR)HomeView.java $(BIN_DIR)CustomButton.class $(BIN_DIR)HomeController.class $(BIN_DIR)Window.class
	$(JC) $(FLAGS) $(UI_DIR)HomeView.java

$(BIN_DIR)SelectionPlayerContainer.class: $(CONTAINER_UI_DIR)SelectionPlayerContainer.java $(BIN_DIR)Container.class \
										  $(BIN_DIR)SelectionPlayerController.class $(BIN_DIR)BoardModel.class
	$(JC) $(FLAGS) $(CONTAINER_UI_DIR)SelectionPlayerContainer.java

$(BIN_DIR)StartGameContainer.class: $(CONTAINER_UI_DIR)StartGameContainer.java $(BIN_DIR)Container.class  \
									$(BIN_DIR)StartGameController.class 
	$(JC) $(FLAGS) $(CONTAINER_UI_DIR)StartGameContainer.java

$(BIN_DIR)Window.class: $(UI_DIR)Window.java $(BIN_DIR)HomeView.class $(BIN_DIR)GameState.class $(BIN_DIR)BoardView.class $(BIN_DIR)CreditView.class \
						$(BIN_DIR)Assert.class $(BIN_DIR)EndGameView.class
	$(JC) $(FLAGS) $(UI_DIR)Window.java


#api bot

#helpers

$(BIN_DIR)Assert.class: $(HELPER_DIR)Assert.java
	$(JC) $(FLAGS) $(HELPER_DIR)Assert.java

$(BIN_DIR)Console.class: $(HELPER_DIR)Console.java
	$(JC) $(FLAGS) $(HELPER_DIR)Console.java

$(BIN_DIR)CopyObject.class: $(HELPER_DIR)CopyObject.java $(BIN_DIR)BoardView.class
	$(JC) $(FLAGS) $(HELPER_DIR)CopyObject.java

$(BIN_DIR)Distance.class: $(HELPER_DIR)Distance.java
	$(JC) $(FLAGS) $(HELPER_DIR)Distance.java

$(BIN_DIR)ModelBoolean.class: $(HELPER_DIR)ModelBoolean.java
	$(JC) $(FLAGS) $(HELPER_DIR)ModelBoolean.java

$(BIN_DIR)RandomCard.class: $(HELPER_DIR)RandomCard.java $(BIN_DIR)Card.class $(BIN_DIR)Hand.class
	$(JC) $(FLAGS) $(HELPER_DIR)RandomCard.java

$(BIN_DIR)Palette.class: $(HELPER_DIR)Palette.java 
	$(JC) $(FLAGS) $(HELPER_DIR)Palette.java

$(BIN_DIR)Position.class: $(HELPER_DIR)Position.java 
	$(JC) $(FLAGS) $(HELPER_DIR)Position.java 


#actions 
$(BIN_DIR)ForceAction.class: $(ACTIONS_DIR)ForceAction.java $(BIN_DIR)Position.class $(BIN_DIR)BoardModel.class $(BIN_DIR)Player.class \
							   $(BIN_DIR)Cell.class $(BIN_DIR)Person.class $(BIN_DIR)ChoiceCardPopUp.class $(BIN_DIR)Console.class
	$(JC) $(FLAGS) $(ACTIONS_DIR)ForceAction.java

$(BIN_DIR)MoveAction.class: $(ACTIONS_DIR)MoveAction.java $(BIN_DIR)Cell.class $(BIN_DIR)Ball.class $(BIN_DIR)Person.class \
							$(BIN_DIR)Distance.class $(BIN_DIR)Position.class $(BIN_DIR)BoardView.class $(BIN_DIR)Assert.class
	$(JC) $(FLAGS) $(ACTIONS_DIR)MoveAction.java

$(BIN_DIR)PassAction.class: $(ACTIONS_DIR)PassAction.java $(BIN_DIR)Cell.class $(BIN_DIR)Ball.class $(BIN_DIR)Person.class \
							$(BIN_DIR)Position.class $(BIN_DIR)Console.class
	$(JC) $(FLAGS) $(ACTIONS_DIR)PassAction.java 

$(BIN_DIR)PlatingAction.class: $(ACTIONS_DIR)PlatingAction.java $(BIN_DIR)Position.class $(BIN_DIR)BoardModel.class $(BIN_DIR)Player.class \
							   $(BIN_DIR)Cell.class $(BIN_DIR)Person.class $(BIN_DIR)ChoiceCardPopUp.class
	$(JC) $(FLAGS) $(ACTIONS_DIR)PlatingAction.java

$(BIN_DIR)ShotAction.class: $(ACTIONS_DIR)ShotAction.java $(BIN_DIR)ConfirmShotPopUp.class $(BIN_DIR)Person.class $(BIN_DIR)Assert.class \
							$(BIN_DIR)Position.class $(BIN_DIR)Cell.class $(BIN_DIR)ConfirmShotPopUp.class \
							$(BIN_DIR)Ball.class
	$(JC) $(FLAGS) $(ACTIONS_DIR)ShotAction.java


#undo/redo
$(BIN_DIR)UndoRedo.class: $(UNDO_REDO_DIR)UndoRedo.java $(BIN_DIR)Player.class $(BIN_DIR)Ball.class $(BIN_DIR)Card.class  \
						  $(BIN_DIR)Hand.class $(BIN_DIR)Assert.class
	$(JC) $(FLAGS) $(UNDO_REDO_DIR)UndoRedo.java 

$(BIN_DIR)BoardSave.class: $(UNDO_REDO_DIR)BoardSave.java $(BIN_DIR)Player.class $(BIN_DIR)Ball.class $(BIN_DIR)Card.class \
						   $(BIN_DIR)Hand.class $(BIN_DIR)TeamType.class $(BIN_DIR)BeefyPerson.class $(BIN_DIR)OrdinaryPerson.class \
						   $(BIN_DIR)SmartPerson.class $(BIN_DIR)FastPerson.class $(BIN_DIR)HardPerson.class 
	$(JC) $(FLAGS) $(UNDO_REDO_DIR)BoardSave.java

#launch program
run:
	$(JEXE) Application

#clean bin dir
clean:
	$(RM) $(BIN_DIR)*

#generate javadoc
doc:
	$(JDOC) -d $(JAVADOC_DIR) $(MODEL_DIR)* $(HELPER_DIR)* $(ENUM_DIR)* $(UI_DIR)*.java $(CONTROLLER_DIR)*.java $(ACTIONS_DIR)* $(UNDO_REDO_DIR)* $(CONTAINER_UI_DIR)* $(CONTAINER_CONTROLLER_DIR)* $(POPUP_UI_DIR)* $(POPUP_CONTROLLER_DIR)*
	#$(JDOC) -d $(JAVADOC_DIR) $(UI_DIR)*.java
	#$(JDOC) -d $(JAVADOC_DIR) $(CONTROLLER_DIR)*.java
	#$(JDOC) -d $(JAVADOC_DIR) $(API_DIR)*.java
	#$(JDOC) -d $(JAVADOC_DIR) $(SRC_DIR)*.java
	#$(JDOC) -d $(JAVADOC_DIR) $(HELPER_DIR)*.java
	#$(JDOC) -d $(JAVADOC_DIR) $(UNDO_REDO_DIR)*.java
	#$(JDOC) -d $(JAVADOC_DIR) $(CONTAINER_UI_DIR)*.java
	#$(JDOC) -d $(JAVADOC_DIR) $(POPUP_UI_DIR)*.java
	#$(JDOC) -d $(JAVADOC_DIR) $(POPUP_CONTROLLER_DIR)*.java
	#$(JDOC) -d $(JAVADOC_DIR) $(CONTAINER_CONTROLLER_DIR)*.java
	#$(JDOC) -d $(JAVADOC_DIR) $(ACTIONS_DIR)*.java

#clean javadoc
docclean:
	$(RM) $(JAVADOC_DIR)*

#dev utility
dev: clean $(BIN_DIR)Application.class

.PHONY: clean test doc
