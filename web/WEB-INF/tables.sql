CREATE TABLE `cells` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `inv_number` varchar(255) NOT NULL,
  `storage_id` int(11) NOT NULL,
  `crn` double NOT NULL,
  `own_date` date NOT NULL,
  `expire_date` date NOT NULL,
  `cost_price` double NOT NULL,
  `image_path` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `sources`
--

CREATE TABLE `sources` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `latitude` text NOT NULL,
  `longitude` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `sources`
--

INSERT INTO `sources` (`id`, `name`, `latitude`, `longitude`) VALUES
(1, 'Goold 1', '123123', '123123'),
(2, 'ewwww', '', ''),
(3, 'Aslan', '123', '123');

-- --------------------------------------------------------

--
-- Структура таблицы `storages`
--

CREATE TABLE `storages` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `source_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `storages`
--

INSERT INTO `storages` (`id`, `name`, `source_id`) VALUES
(7, 'Aslan', 3),
(8, 'asdfasdfasdf', 2);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `cells`
--
ALTER TABLE `cells`
ADD PRIMARY KEY (`id`),
ADD KEY `fk_cells_storages` (`storage_id`);

--
-- Индексы таблицы `sources`
--
ALTER TABLE `sources`
ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `storages`
--
ALTER TABLE `storages`
ADD PRIMARY KEY (`id`),
ADD KEY `fk_storages_sources` (`source_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `cells`
--
ALTER TABLE `cells`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `sources`
--
ALTER TABLE `sources`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `storages`
--
ALTER TABLE `storages`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `cells`
--
ALTER TABLE `cells`
ADD CONSTRAINT `fk_cells_storages` FOREIGN KEY (`storage_id`) REFERENCES `storages` (`id`);

--
-- Ограничения внешнего ключа таблицы `storages`
--
ALTER TABLE `storages`
ADD CONSTRAINT `fk_storages_sources` FOREIGN KEY (`source_id`) REFERENCES `sources` (`id`);
