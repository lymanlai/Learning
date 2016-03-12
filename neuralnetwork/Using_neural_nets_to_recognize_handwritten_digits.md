# using neural nets to recognize handwritten digits
===========================

The human visual system is one of the wonders of the world.
Consider the following sequence of handwritten digits:

2123145464523456

Most people effortlessly recognize those digits as 504192. That ease is deceptive. 

In each hemisphere of our brain, humans have a primary visual cortex, also known as V1, containing 140 million neurons, with tens of billions of connections 140 million neurons with tens of billions of connections between them. 

And yet human vision involves not just V1, but an entire series of visual cortices - V2, V3, V4, and V5 - doing progressively more complex image processing. 

We carry in our heads supercomputer, tuned by evolution over hundreds of milions of years, and superbly adapted to understand the visual world. Recognizing handwritten digit isn't easy.

Rather, we humans are stupendously, astoundingly good at marking sense of what our eyes show us.

But nearly all that work is done unconsciously.

And so we don't usually appreciate how tough a problem our visual systems solve.

===========================

The difficulty of visual pattern recognition becomes apparent if you attempt to write a computer progrem to recognize digits like those above. 

What seems easy when we do it ourselves suddenly becomes extremely difficult.

Simple intuitions about how we recognize shapes - "a 9 has a loop at the top, and a vertical stroke in the bottom right" - turn out to be not so simple to express algorithmically.

When you try to make such rules precise, you quickly get lost in a morass of exceptions and caveats and special cases.

It seems hopeless.

===========================

Neural networks approach the problem in a different way. The idea is to take a large number of handwritten digits, known as training examples,

2123145464523456

and then develop a system which can learn from those training examples.

In other words, the neural network uses the examples to automatically infer rules for recognizing handwritten digits.

Furthermore, by increasing the number of trainning examples, the network can learn more about handwriting, and so improve its accuracy

So while I've shown just 100 training digits above, prehaps we could build a better handwriting recognizer by using thousands or even millions or billions of training examples.

===========================

In this chapter we'll write a computer program implementing a neural network that learns to recognize handwritten digits. 

The program is just 74 lines long, and uses no special neural network libraries. 

But this short program can recognize digits with an accuracy over 96 percent, without human intervention.

Furthermore, in later chapters we'll develop ideas which can improve accuracy to over 99 percent.

In fact, the best commercial neural networks are now so good that they are used by banks to process cheques, and by post offices to recognize address.

===========================

We're focusing on handwriting recognition because it's an excellent prototype problem for learning about neural networks in general.

As a prototype it hits a sweet spot: it's challenging - it's no small feat to recognize handwritten digits - but it's not so difficult as to require an extremely complicated solution, or tremendous computational power.

Furthermore, it's a great way to develop more advanced techniques, such as deep learning.

And so throughout the book we'll return repeatedly to the problem of handwriting recognition.

Later in the book, we'll dicuss how these ideas may be applied to other problems in computer vision, and alos in speech, natural language processing, and other domains.

===========================

Of course, if the point of the chapter was only to write a computer program to recognize handwritten digits, then the chapter would be much shorter!

But along the way we'll develop many key ideas about neural networks, including two important types of artificial neuron (the perceptron and the sigmoid neuron), and the standard learning algorithm for neural networks, known as stochastic gradient descent.

Throughout, I focus on explaining why things are done the way they are, and on building your neural networks intuition.

That requires a lengthier discussion than if I just presented the basic mechanics of what's going on, but it's worth it for the deeper understanding you'll attain.

Amongst the payoffs, by the end of the chapter we'll be in position to understand what deep learning is, and why it matters.


## Perceptrons
===========================

What is a neural network? To get started, I'll explain a type of artificial neuron called a perceptron.

Perceptrons were developed in the 1950s and 1960s by the scientist Frank Rosenblatt, inspired by earlier work by Warren McCulloch and Walter Pitts.

Today, it's more common to use other models of artificial neurons - in this book, and in nuch modern work on neural networks, the main neuron model used is one called the sigmoid neuron.

We'll get to sigmoid neurons shortly.

But to understand why sigmoio neurons are defined the way they are, it's worth taking the time to first understand perceptrons.

So how do perceptrons work? A perceptron takes several binary input, x1, x2, ...., and produces a single binary output:




In the example shown the perceptron has three input x1, x2, x3. In general it could have more or fewer inputs.

Rosenblatt proposed a simple rule to compute the output.

He inroduced weights, w1, w2, ..., real numbers expressing the importance of the respective inputs to the output. 

The neuron's output, 0, or 1, is determined by whether the weighted sum 