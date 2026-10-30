package com.example.lms;

/** Course, unit and test data. Order everywhere: AI, FSD, CN, ML, CC. */
final class SeedData {
    private SeedData() {}

    // code, name, short description
    static final String[][] COURSES = {
        {"AI", "Artificial Intelligence", "Learn how machines search, reason and represent knowledge, from heuristic search and game playing to expert systems."},
        {"FSD", "Full Stack Development", "Build complete web apps: HTML, CSS, JavaScript and React on the front-end, with Servlets, JSP, Spring and Hibernate on the back-end."},
        {"CN", "Computer Networks", "Understand how data travels across networks: layers, routing, TCP and UDP, DNS, HTTP and basic network security."},
        {"ML", "Machine Learning", "See how computers learn from data: concept learning, decision trees, neural networks, SVMs, Bayesian learning and genetic algorithms."},
        {"CC", "Cloud Computing", "Explore cloud models, virtualization, platforms like AWS and Azure, cloud security and SLA management."}
    };

    // 5 units per course: {title, topics}
    static final String[][][] UNITS = {
        { // AI
            {"Introduction & Problem Solving", "History of AI, state-space search, heuristic search, A*, constraint satisfaction, game playing and alpha-beta pruning."},
            {"Logic & Logic Programming", "Propositional logic, natural deduction, resolution, predicate logic and logic programming."},
            {"Knowledge Representation", "Semantic networks, frames, case grammars and the semantic web."},
            {"Uncertainty & Intro to ML", "Bayesian belief networks, certainty factors, Dempster-Shafer theory, supervised vs unsupervised learning."},
            {"Expert Systems", "Phases and architecture of expert systems, rule-based systems, truth maintenance and applications."}
        },
        { // FSD
            {"Building Blocks, HTML & CSS", "Front-end vs back-end, MVC, web services, JSON, HTML5 basics, CSS box model, positioning and forms."},
            {"JavaScript & React Basics", "JavaScript, DOM, events, form validation, jQuery, React components and state."},
            {"React Router, Forms & Servlets", "Routing, controlled forms, servlet life cycle, cookies, sessions and database connection."},
            {"JSP & Spring Framework", "JSP pages, JavaBeans, Spring MVC, controllers, form tags, CRUD and validation."},
            {"Hibernate & Web Services", "Hibernate architecture, mappings, HQL, caching, Spring integration and RESTful web services."}
        },
        { // CN
            {"Introduction & Physical Layer", "Network types, hardware and software, OSI and TCP/IP models, transmission media and multiplexing."},
            {"Data Link Layer & MAC", "Framing, error detection, sliding window, ALOHA, CSMA, Ethernet and Wi-Fi (802.11)."},
            {"Network Layer", "Routing algorithms, shortest path, distance vector, link state, multicast and congestion control."},
            {"Internetworking & Transport Layer", "Tunneling, IPv4, IPv6, internet control protocols, UDP and TCP."},
            {"Application Layer & Security", "DNS, email, the WWW, HTTP and basic cryptography."}
        },
        { // ML
            {"Introduction & Concept Learning", "Learning problems, types of learning, hypothesis space, Find-S and candidate elimination."},
            {"Decision Trees & Neural Networks", "Decision tree learning, Occam's razor, the neuron model, activation functions and feed-forward networks."},
            {"SVM & Bayesian Learning", "Linear and non-linear SVM, Bayes theorem, Naive Bayes classifier and the EM algorithm."},
            {"Computational Learning & Instance-Based", "PAC learning, sample complexity, k-NN, locally weighted regression and case-based reasoning."},
            {"Genetic Algorithms", "Biological motivation, representing hypotheses, genetic operators, fitness and genetic programming."}
        },
        { // CC
            {"Introduction & Architecture", "Cloud history, reference model, types of clouds, economics and open challenges."},
            {"Virtualization", "Virtualization types, pros and cons, Xen, VMware, Hyper-V and migrating to the cloud."},
            {"Cloud Platforms & Programming", "AWS, Google App Engine, Microsoft Azure, GFS, BigTable, EC2 and S3."},
            {"Security in the Cloud", "Cloud security challenges, SaaS security, data security risks and identity."},
            {"SLA & Advanced Topics", "SLA types and life cycle, green cloud computing, market-oriented and federated clouds."}
        }
    };

    // Unit 1 test for each course: "question|A|B|C|D|correct letter"
    static final String[][] UNIT1_TEST = {
        { // AI
            "Who is credited with coining the term Artificial Intelligence?|Alan Turing|John McCarthy|Marvin Minsky|Herbert Simon|B",
            "Which search explores the shallowest nodes first?|Breadth-first search|Depth-first search|Hill climbing|Alpha-beta pruning|A",
            "Which search uses a heuristic function to guide the search?|Breadth-first search|A* search|Depth-first search|Blind search|B",
            "A* search uses which evaluation function?|f(n) = g(n) + h(n)|f(n) = g(n) - h(n)|f(n) = h(n) only|f(n) = n|A",
            "Which of these is an uninformed (blind) search?|Hill climbing|A*|Breadth-first search|Best-first search|C",
            "In state-space search, a solution is a path from the ...|Goal to a dead end|Any node to any node|Root to the largest leaf|Initial state to a goal state|D",
            "Which algorithm is used in two-player games to choose the best move?|Bubble sort|Dijkstra|Minimax|Kruskal|C",
            "Alpha-beta pruning is used to ...|Increase the size of the tree|Reduce the number of nodes evaluated in minimax|Sort the nodes|Remove the heuristic|B",
            "Which problem is defined using variables, domains and constraints?|Sorting problem|Routing problem|Compression problem|Constraint satisfaction problem|D",
            "In game playing, an evaluation function estimates ...|Memory usage|Network speed|How good a board position is|The number of players|C"
        },
        { // FSD
            "Which part of a web application runs in the user's browser?|Front-End|Back-End|Database|Application server|A",
            "In MVC, which component holds the data and business logic?|View|Model|Controller|Router|B",
            "What does JSON stand for?|Java Standard Object Naming|JavaScript Online Network|JavaScript Object Notation|Joint Syntax Object Notation|C",
            "Converting an object into a JSON string is called ...|Parsing|Rendering|Compiling|Serialization|D",
            "Converting a JSON string back into an object is called ...|Parsing|Serialization|Styling|Routing|A",
            "Which HTML tag creates a hyperlink?|<link>|<a>|<href>|<url>|B",
            "Which HTML tag is used for a short inline quotation?|<blockquote>|<cite>|<q>|<quote>|C",
            "In the CSS box model, which layer sits between the content and the border?|Margin|Outline|Position|Padding|D",
            "Which CSS position value fixes an element relative to the browser window?|fixed|relative|static|inherit|A",
            "Which CSS feature makes a page adapt to different screen sizes?|Outline|Media queries|Box shadow|Float|B"
        },
        { // CN
            "How many layers does the OSI reference model have?|5|6|7|4|C",
            "Which OSI layer deals with the transmission of raw bits?|Data Link|Physical|Network|Transport|B",
            "Which guided medium transmits data as light signals?|Twisted pair|Coaxial cable|Fiber optic cable|Microwave|C",
            "Which cable has pairs of insulated copper wires twisted together?|Twisted pair|Coaxial cable|Fiber optic cable|Waveguide|A",
            "A network that covers a single building or campus is a ...|WAN|LAN|MAN|PAN|B",
            "A network that spans a country or continent is a ...|PAN|LAN|MAN|WAN|D",
            "FDM stands for ...|Frequency Division Multiplexing|Fast Data Management|Fixed Digital Modulation|Frame Delay Method|A",
            "TDM shares a channel by dividing it into ...|Frequencies|Time slots|Codes|Cables|B",
            "CDM separates the signals of different users using ...|Time slots|Frequency bands|Unique codes|Cable colours|C",
            "Which of these is a wireless transmission medium?|Radio waves|Twisted pair|Coaxial cable|Fiber optic cable|A"
        },
        { // ML
            "A program learns from experience E for task T with measure P if its performance on T, measured by P, ...|Decreases with E|Improves with E|Stays constant|Depends only on T|B",
            "Learning from labelled training data is called ...|Unsupervised learning|Reinforcement learning|Supervised learning|Clustering|C",
            "Grouping unlabelled data is an example of ...|Supervised learning|Unsupervised learning|Concept learning|Classification|B",
            "In concept learning, the set of all possible hypotheses is called the ...|Version space|Training set|Hypothesis space|Feature set|C",
            "Which algorithm finds the maximally specific hypothesis?|Candidate Elimination|Find-S|ID3|k-NN|B",
            "The version space is the set of hypotheses that are ...|Consistent with all training examples|Always the most specific|Randomly chosen|Inconsistent with the data|A",
            "The candidate elimination algorithm maintains which two boundaries?|Mean and variance|Specific and general boundaries|Input and output|Training and testing|B",
            "Inductive bias is the set of assumptions a learner uses to ...|Delete training data|Speed up hardware|Label data manually|Predict outputs for unseen inputs|D",
            "Which of these is a common issue in machine learning?|Overfitting|Too many OSI layers|Packet loss|Cable length|A",
            "Which type of learning uses rewards and penalties?|Supervised learning|Unsupervised learning|Reinforcement learning|Concept learning|C"
        },
        { // CC
            "Which cloud service model delivers ready-to-use software over the internet?|IaaS|PaaS|SaaS|DaaS|C",
            "Which model provides a platform to develop and deploy applications?|PaaS|SaaS|IaaS|None of these|A",
            "Which model provides virtual machines, storage and networks?|SaaS|IaaS|PaaS|FaaS|B",
            "A cloud shared by many organisations over the internet is a ...|Private cloud|Hybrid cloud|Community cloud|Public cloud|D",
            "A cloud used by a single organisation is a ...|Public cloud|Private cloud|Hybrid cloud|Community cloud|B",
            "A mix of public and private clouds is a ...|Hybrid cloud|Public cloud|Private cloud|Local cloud|A",
            "Which is a key economic benefit of cloud computing?|Fixed hardware cost|Buying all servers upfront|Pay-per-use pricing|Higher maintenance cost|C",
            "Which of these is an open challenge in cloud computing?|Faster typing|Larger screens|Cheaper paper|Data security and privacy|D",
            "Which technology lets one physical server run many virtual servers?|Compilation|Virtualization|Packet switching|Compression|B",
            "Cloud computing delivers computing services over the ...|Internet|USB cable|Bluetooth only|Printer port|A"
        }
    };
}
