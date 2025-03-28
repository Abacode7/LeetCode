/** Basic SQL Queries
 * Select: select * from Users u where u.id = 1;
 *
 * Insert: insert into Users(name, age, phone) values("Tobi", 45, 997);
 *
 * Update: update Users u set u.age = 27 where u.id = 5;
 *
 * Delete: delete from Users u where u.id = 7;
 *
 * Data Structures
 * Stack
 * Uses LIFO (Last In First Out)
 * Pushes and pops elements from the top of the list
 * NB: top here means the conceptual top as in a stack of plates,
 * not the index 0 position in the list. Top here is a variable index.
 * Example: Follows from the List Inteface
 * Stack<Integer> stack = new Stack<>();
 * stack.push(5); stack.peek(); stack.pop(); stack.empty()
 *
 * Queues
 * Uses FIFO - First In First Out
 * Pushes element into the rears and removes element from the front.
 * Example: Follows the Queue Interface
 * Queue<Integer> queue = new LinkedList<>();
 * queue.offer(5), queue.peek(),queue.poll(), queue.isEmpty();
 *
 * APIE - Abstraction, Polymorphism, Inheritance & Encapsulation
 * https://www.mygreatlearning.com/blog/polymorphism-in-java/
 * https://www.techtarget.com/searchapparchitecture/definition/object-oriented-programming-OOP#:~:text=Object%2Doriented%20programming%20(OOP)%20is%20a%20computer%20programming%20model,rather%20than%20functions%20and%20logic.
 *
 */
package com.company;


import java.util.LinkedList;
import java.util.Queue;

public class MIU_InterviewPrep {
    /**
     Interview Questions
     1. What is Inheritance and how can it be used
     Ans: This is a property of OOP where subclasses inherit attributes and functions
     from superclasses. It aids code reuseability.

     <Since my preferred language is Java>
     2. Does Java support multiple inheritance
     Ans: No, java does not support multiple inheritance.
     A subclass can only extend from one super class.

     3. Why (for the above)?
     Ans: I mentioned for clean code reasons.
     Correct Ans: He gave me partial credit for this and mentioned a more
     detailed answer (can't recall). You'll need to look this up.

     4. What does the 'this' keyword mean
     Ans: It is used to refer to the current instance (object) of a class.
     */

    /** Coding Question
     * Given tree
     *      1
     *    / | \
     *  2   3  4
     *    / | \
     *  5   6  7
     *
     *  Print out tree in format: 1 2 3 4 5 6 7
     */
    public static void main(String[] args){
        MNode seven = new MNode(7, null, null, null);
        MNode six = new MNode(6, null, null, null);
        MNode five = new MNode(5, null, null, null);
        MNode four = new MNode(4, null, null, null);
        MNode three = new MNode(3, five, six, seven);
        MNode two = new MNode(2, null, null, null);
        MNode one = new MNode(1, two, three, four);

        one.printTreeByLevel();
    }
}

class MNode{
    int data;
    MNode left;
    MNode middle;
    MNode right;

    public MNode(int data, MNode left, MNode middle, MNode right){
        this.data = data;
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    /** Implement this method **/
    public void printTreeByLevel(){
        Queue<MNode> queue = new LinkedList<>();
        queue.add(this);

        while(!queue.isEmpty()){
            MNode currentNode = queue.poll();
            if(currentNode.left != null) queue.offer(currentNode.left);
            if(currentNode.middle != null) queue.offer(currentNode.middle);
            if(currentNode.right != null) queue.offer(currentNode.right);
            System.out.println(currentNode.data);
        }
    }
}
